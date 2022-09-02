package com.magdev.presentation.main.home

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.magdev.R
import com.magdev.databinding.FragmentHomeBinding
import com.magdev.domain.weather.WeatherResponse
import com.magdev.infrastructure.extensions.formatDegree
import com.magdev.infrastructure.extensions.loadWeatherIcon
import com.magdev.presentation.common.viper.AbstractViperFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class HomeFragment : AbstractViperFragment<HomePresenter>(R.layout.fragment_home),
                     IHomeView {

    private val adapter = WeekWeatherAdapter()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var location: Location? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @InjectPresenter
    lateinit var presenter: HomePresenter

    @ProvidePresenter
    override fun providePresenter(): HomePresenter {
        return presenterProvider.get()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        initViews()
        getUserLocation()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun setPending(isPending: Boolean) {
        binding.progressBar.visibility = if (isPending) View.VISIBLE else View.GONE
        if(isPending) {
            binding.noDataText.visibility = View.GONE
            binding.contentLayout.visibility = View.GONE
        }
    }

    override fun setData(weatherResponse: WeatherResponse) {
        if(weatherResponse.current != null) {
            val degValue = weatherResponse.current.temp?.toInt() ?: 0
            val feelLikeDegValue = weatherResponse.current.feelsLike?.toInt() ?: 0
            val iconId = weatherResponse.current.weather?.firstOrNull()?.icon

            binding.degText.text = getString(R.string.degree_value, degValue.formatDegree())
            binding.feelLikeText.text = getString(R.string.feels_like_value, feelLikeDegValue.formatDegree())
            binding.cityText.text = when {
                weatherResponse.name != null -> weatherResponse.name
                location != null -> getString(R.string.your_location)
                else -> getString(R.string.default_location)
            }

            if(iconId != null) {
                binding.weatherIcon.visibility = View.VISIBLE
                binding.weatherIcon.loadWeatherIcon(iconId)
            } else {
                binding.weatherIcon.visibility = View.GONE
            }

            binding.noDataText.visibility = View.GONE
            binding.contentLayout.visibility = View.VISIBLE
        } else {
            binding.noDataText.visibility = View.VISIBLE
            binding.contentLayout.visibility = View.GONE
        }

        if(!weatherResponse.daily.isNullOrEmpty()) {
            adapter.setData(weatherResponse.daily)
        }
    }

    private fun initViews() {
        binding.swipeToRefresh.setOnRefreshListener {
            getUserLocation()
            binding.swipeToRefresh.isRefreshing = false
        }

        binding.weekRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false)

            setHasFixedSize(true)
            adapter = this@HomeFragment.adapter
        }
    }

    private fun getUserLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                location = task.result
                presenter.getWeather(location)
            }
        } else {
            presenter.getWeather(null)
        }
    }

    companion object {
        fun newInstance(): Fragment {
            return HomeFragment()
        }
    }
}
