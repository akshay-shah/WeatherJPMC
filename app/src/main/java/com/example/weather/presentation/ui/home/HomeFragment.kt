package com.example.weather.presentation.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: ArrayAdapter<String>
    lateinit var locationManager: LocationManager
    private var hasGps: Boolean = false
    private var onConfigChanged: Boolean = false


    val gpsLocationListener: LocationListener =
        LocationListener { location -> viewModel.getWeather(location.latitude, location.longitude) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        binding.lifecycleOwner = this@HomeFragment
        binding.viewModel = this@HomeFragment.viewModel
        binding.searchViewCitySearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //TODO("Not yet implemented")
            }

            override fun afterTextChanged(string: Editable?) {
                viewModel.searchCity(SpannableStringBuilder(string).toString())
            }
        })
        binding.searchViewCitySearch.setOnItemClickListener { parent, view, position, id ->
            view.clearFocus()
            val imm: InputMethodManager? =
                context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(view.applicationWindowToken, 0)
            viewModel.onListItemClickListener(position)
        }
        viewModel.cityCountryNameModel.observe(viewLifecycleOwner) {
            adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_list_item_1,
                it.map { it.name })
            binding.searchViewCitySearch.setAdapter(adapter)
            adapter.notifyDataSetChanged()
        }
        requestLocationUpdates()
        return binding.root
    }

    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (hasGps && !onConfigChanged) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 1000000, 0F, gpsLocationListener
            )
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        onConfigChanged =
            newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE || newConfig.orientation == Configuration.ORIENTATION_PORTRAIT
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            if (it.get(Manifest.permission.ACCESS_FINE_LOCATION) == true && it.get(Manifest.permission.ACCESS_FINE_LOCATION) == true) {
                if (hasGps) {
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 1000000, 0F, gpsLocationListener
                    )
                } else {
                    AlertDialog.Builder(requireContext()).setTitle("Enable GPS")
                        .setMessage("Please enable GPS in settings")
                        .setPositiveButton("OK", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                                startActivity(intent)
                            }
                        }).create().show()
                }
            }
        }.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    companion object {

        @JvmStatic
        @BindingAdapter("bind:imageUrl")
        fun setImageUrl(imageView: ImageView, url: String?) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}

