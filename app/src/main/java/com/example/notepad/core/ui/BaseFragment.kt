package com.example.notepad.core.ui
//
//import android.graphics.Color
//import android.graphics.Rect
//import android.os.Build
//import android.os.Bundle
//import android.com.example.notepad.core.util.TypedValue
//import android.view.*
//import android.view.View.*
//import androidx.core.content.ContextCompat
//import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.NavController
//import androidx.navigation.fragment.findNavController
//import androidx.navigation.ui.NavigationUI
//import dagger.android.support.DaggerFragment
//import ir.part.app.merat.MeratApp
//import ir.part.app.merat.R
//import ir.part.app.merat.core.com.example.notepad.core.util.DateUtil
//import ir.part.app.merat.core.com.example.notepad.core.util.ExceptionHelper
//import ir.part.app.merat.core.com.example.notepad.core.util.analytics.MatomoAnalyticsHelper
//import ir.part.app.merat.features.home.ui.HomeActivity
//import ir.part.app.merat.features.user.ui.UserLoginDialogFragment
//import kotlinx.android.synthetic.main.toolbar_personal_info.view.*
//import kotlinx.android.synthetic.main.toolbar_general.*
//import javax.inject.Inject
//
//interface OnKeyboardVisibilityListener {
//    fun onKeyboardVisibilityChanged(visible: Boolean)
//}
//
//open class BaseFragment : DaggerFragment(), OnKeyboardVisibilityListener {
//
//    lateinit var navController: NavController
//    open val menuItem = R.menu.menu_general
//
//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory
//
//    @Inject
//    lateinit var analyticsHelper: MatomoAnalyticsHelper
//
//    @Inject
//    lateinit var dateUtil: DateUtil
//
//    open val visibilityStatusBar = true
//
//    open val keyboardVisibilityListener = false
//
//    lateinit var globalLayoutListener: ViewTreeObserver.OnGlobalLayoutListener
//    var parentView: View? = null
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        navController = findNavController()
//        visibilityStatusBar(visibilityStatusBar)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(menuItem, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    open fun setupToolbar() {
//
//        (activity as HomeActivity).setSupportActionBar(toolbar_general)
//        (activity as HomeActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
//        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        (activity as HomeActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
//        setHasOptionsMenu(true)
//        if (toolbar_general.tv_toolbar_go_to_home != null) {
//            toolbar_general.tv_toolbar_go_to_home.setOnClickListener {
//                safeNavigate(
//                    navController,
//                    R.id.personalInfoFragment,
//                    MeratApp.currentStartDestination
//                )
//            }
//        } else NavigationUI.setupActionBarWithNavController(activity as HomeActivity, navController)
//
//    }
//
//    fun visibilityStatusBar(showStatusBar: Boolean) {
//        if (showStatusBar) {
//            requireActivity().window.setFlags(
//                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN
//            )
//            requireActivity().window.decorView.systemUiVisibility =
//                SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                requireActivity().window.statusBarColor =
//                    ContextCompat.getColor(requireContext(), R.color.colorWhite)
//            }
//        } else {
//            requireActivity().window.decorView.systemUiVisibility =
//                SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                requireActivity().window.statusBarColor = Color.TRANSPARENT
//            }
//
//        }
//    }
//
//    open fun checkForResetApp(
//        errorCode: String?,
//        errorMessage: String?,
//        submitCallback: ((Int) -> Unit)? = null,
//        cancelCallback: ((Int) -> Unit)? = null,
//        submitElseCallback: ((Int) -> Unit)? = null,
//        elseRun: ((Int) -> Unit)? = null
//
//    ) {
//        when {
//            (errorCode ?: "") == ExceptionHelper.ErrorCodes.NoInternetConnection.name -> {
//                val connectionErrorDialog =
//                    getConnectionErrorDialog(requireContext(), navController = navController)
//                connectionErrorDialog.submitCallback = submitCallback
//                if (cancelCallback != null) {
//                    connectionErrorDialog.cancelCallback = cancelCallback
//                }
//                connectionErrorDialog.safeShow(childFragmentManager, "no_internet_connection")
//            }
//            (errorCode ?: "") == "10" -> {
//                when (navController.currentDestination?.id) {
//                    R.id.splashFragment,
//                    R.id.userHomePageFragment,
//                    R.id.userRegisterFragment,
//                    R.id.userLoginFragment,
//                    R.id.userForgetPasswordFragment,
//                    R.id.userForgetPasswordVerificationFragment -> return
//                    else -> {
//                        val userLoginDialogFragment = UserLoginDialogFragment()
//                        userLoginDialogFragment.submitCallback = submitCallback
//                        userLoginDialogFragment.safeShow(
//                            childFragmentManager,
//                            "userLoginDialogFragment"
//                        )
//                    }
//                }
//            }
//            else -> elseRun?.invoke(0) ?: run {
//                val errorDialog = getErrorDialog(
//                    requireContext(),
//                    getString(R.string.msg_general_error_title),
//                    errorMessage ?: getString(R.string.msg_general_error),
//                    if(submitElseCallback==null && submitCallback==null) requireContext().getString(R.string.label_dialog_submit) else requireContext().getString(R.string.btn_retry)
//                )
//                errorDialog.submitCallback = submitElseCallback?:submitCallback
//                errorDialog.safeShow(childFragmentManager, "errorMessage")
//            }
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
//    }
//
//
//    private fun setKeyboardVisibilityListener(onKeyboardVisibilityListener: OnKeyboardVisibilityListener) {
//        parentView =
//            (requireActivity().findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)
//
//        globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
//
//            private var alreadyOpen: Boolean = false
//            private val defaultKeyboardHeightDP = 100
//            private val EstimatedKeyboardDP =
//                defaultKeyboardHeightDP + if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) 48 else 0
//            private val rect = Rect()
//
//            override fun onGlobalLayout() {
//                parentView?.let {
//                    val estimatedKeyboardHeight = TypedValue.applyDimension(
//                        TypedValue.COMPLEX_UNIT_DIP,
//                        EstimatedKeyboardDP.toFloat(),
//                        it.resources.displayMetrics
//                    ).toInt()
//                    it.getWindowVisibleDisplayFrame(rect)
//                    val heightDiff = it.rootView.height - (rect.bottom - rect.top)
//                    val isShown = heightDiff >= estimatedKeyboardHeight
//
//                    if (isShown == alreadyOpen) {
//                        return
//                    }
//                    alreadyOpen = isShown
//
//
//                    onKeyboardVisibilityListener.onKeyboardVisibilityChanged(isShown)
//                }
//            }
//        }
//        parentView?.viewTreeObserver?.addOnGlobalLayoutListener(globalLayoutListener)
//    }
//
//    override fun onKeyboardVisibilityChanged(visible: Boolean) {}
//
//    override fun onStart() {
//        super.onStart()
//        if (keyboardVisibilityListener)
//            setKeyboardVisibilityListener(this)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        parentView?.viewTreeObserver?.removeOnGlobalLayoutListener(globalLayoutListener)
//    }
//
//}