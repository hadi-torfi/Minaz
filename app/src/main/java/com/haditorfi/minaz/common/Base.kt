package com.haditorfi.minaz.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class MyFragment : Fragment(), MyView {

}

abstract class MyActivity : AppCompatActivity(), MyView {

}

interface MyView {

}

abstract class MyViewModel : ViewModel() {

}
