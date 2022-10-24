package uz.mamadalievdev.rawg.ui.publishers

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.mamadalievdev.rawg.R
import uz.mamadalievdev.rawg.data.base.BaseFragment
import uz.mamadalievdev.rawg.databinding.FragmentDashboardBinding
import uz.mamadalievdev.rawg.ui.adapters.PublisherAdapter

@AndroidEntryPoint
class DashboardFragment :
    BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {
    private val viewModel: DashboardViewModel by viewModels()

    private val adapter by lazy {
        PublisherAdapter()
    }

    override fun onViewCreate() {
        binding.apply {
            publishersList.layoutManager = GridLayoutManager(requireContext(),
                2, GridLayoutManager.VERTICAL, false)
            publishersList.adapter = adapter
        }

        adapter.setItemClickListener {
            val bundle = bundleOf("ID" to it)
            navController.navigate(R.id.action_navigation_home_to_gameDetailsFragment, bundle)
        }

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getPublishers()
        }

        adapter.setItemClickListener {
            val bundle = bundleOf("ID" to it)
            navController.navigate(R.id.action_navigation_dashboard_to_publisherDetailsFragment,
                bundle)
        }

        viewModel.getPublishers()
        viewModel.publishersLiveData.observe(viewLifecycleOwner) {
            adapter.setPublishers(it)
        }
    }
}