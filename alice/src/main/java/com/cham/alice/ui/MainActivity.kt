package com.cham.alice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.blankj.utilcode.util.BarUtils
import com.cham.alice.R
import com.cham.alice.base.BaseActivity
import com.cham.alice.base.NoViewModel
import com.cham.alice.databinding.ActivityMainBinding
import com.cham.alice.ui.one.OneFragment


/**
 * Hello World
 * Date: 2019/12/30
 * Author: Cham
 */
class MainActivity :BaseActivity<NoViewModel, ActivityMainBinding>() {
    var currentFragment: Fragment = Fragment()

    private val homeFragment by lazy { OneFragment.newInstance() }
    override fun providerVMClass(): Class<NoViewModel> =NoViewModel::class.java
    override fun initLayout(): Int = R.layout.activity_main
    override fun initView(savedInstanceState: Bundle?) {
        BarUtils.setStatusBarLightMode(this, true)

        switchFragment(homeFragment).commit()
        binding.navView.run {
            itemIconTintList = null
            setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.nv_one ->
                        switchFragment(homeFragment).commit()
                    R.id.nv_two ->{}

                    R.id.nv_thr ->{}

                    R.id.nv_for ->{}

                }
                true
            }

        }
    }







    fun switchFragment(targetFragment : Fragment): FragmentTransaction {
        var     transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        if(!targetFragment.isAdded){
            transaction.hide(currentFragment)
            transaction.add(R.id.fl_content, targetFragment,targetFragment::class.java.name)
        }else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment)
        }
        currentFragment = targetFragment
        return   transaction

    }
}