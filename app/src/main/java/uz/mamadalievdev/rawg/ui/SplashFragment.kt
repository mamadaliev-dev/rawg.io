package uz.mamadalievdev.rawg.ui

import android.os.CountDownTimer
import uz.mamadalievdev.rawg.R
import uz.mamadalievdev.rawg.data.base.BaseFragment
import uz.mamadalievdev.rawg.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override fun onViewCreate() {
        object : CountDownTimer(2000, 100) {
            override fun onFinish() {
                navController.navigate(R.id.action_splashFragment_to_navigation_home)
            }

            override fun onTick(value: Long) {

            }
        }.start()
    }
}