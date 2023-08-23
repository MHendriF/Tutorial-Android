package com.mhendrif.mysimplelogin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.mhendrif.core.SessionManager
import com.mhendrif.core.UserRepository
import com.mhendrif.mysimplelogin.databinding.ActivityHomeBinding

class HomeActivity : BaseSplitActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var userRepository: UserRepository
    lateinit var manager: SplitInstallManager

    /** Listener used to handle changes in state for install requests. */
    private val listener = SplitInstallStateUpdatedListener { state ->
        val multiInstall = state.moduleNames().size > 1
        val langsInstall = state.languages().isNotEmpty()

        val names = if (langsInstall) {
            // We always request the installation of a single language in this sample
            state.languages().first()
        } else state.moduleNames().joinToString(" - ")

        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                //  In order to see this, the application has to be uploaded to the Play Store.
                displayLoadingState(state, getString(R.string.downloading, names))
            }
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                /*
                  This may occur when attempting to download a sufficiently large module.

                  In order to see this, the application has to be uploaded to the Play Store.
                  Then features can be requested until the confirmation path is triggered.
                 */
                manager.startConfirmationDialogForResult(state, this, CONFIRMATION_REQUEST_CODE)
            }
            SplitInstallSessionStatus.INSTALLED -> {
                onSuccessfulLoad(names, launch = !multiInstall)
            }

            SplitInstallSessionStatus.INSTALLING -> displayLoadingState(
                state,
                getString(R.string.installing, names)
            )
            SplitInstallSessionStatus.FAILED -> {
                toastAndLog(getString(R.string.error_for_module, state.errorCode(),
                    state.moduleNames()))
            }
        }
    }

    /** This is needed to handle the result of the manager.startConfirmationDialogForResult
    request that can be made from SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION
    in the listener above. */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CONFIRMATION_REQUEST_CODE) {
            // Handle the user's decision. For example, if the user selects "Cancel",
            // you may want to disable certain functionality that depends on the module.
            if (resultCode == Activity.RESULT_CANCELED) {
                toastAndLog(getString(R.string.user_cancelled))
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private val moduleChat by lazy { getString(R.string.module_feature_chat) }

    private val installableModules by lazy {
        listOf(moduleChat)
    }

    private val clickListener by lazy {
        View.OnClickListener {
            when (it.id) {
                R.id.fab -> loadAndLaunchModule(moduleChat)
                R.id.btn_logout -> {
                    userRepository.logoutUser()
                    moveToMainActivity()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        manager = SplitInstallManagerFactory.create(this)

        val sesi = SessionManager(this)
        userRepository = UserRepository.getInstance(sesi)
        setupView()
    }

    private fun setupView() {
        binding.tvWelcome.text = "Welcome ${userRepository.getUser()}"
        setupClickListener()
    }

    /** Set all click listeners required for the buttons on the UI. */
    private fun setupClickListener() {
        setClickListener(R.id.btn_logout, clickListener)
        setClickListener(R.id.fab, clickListener)
    }

    private fun setClickListener(id: Int, listener: View.OnClickListener) {
        findViewById<View>(id).setOnClickListener(listener)
    }

    override fun onResume() {
        // Listener can be registered even without directly triggering a download.
        manager.registerListener(listener)
        super.onResume()
    }

    override fun onPause() {
        // Make sure to dispose of the listener once it's no longer needed.
        manager.unregisterListener(listener)
        super.onPause()
    }

    private fun moveToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    /**
     * Load a feature by module name.
     * @param name The name of the feature module to load.
     */
    private fun loadAndLaunchModule(name: String) {
        updateProgressMessage(getString(R.string.loading_module, name))
        // Skip loading if the module already is installed. Perform success action directly.
        if (manager.installedModules.contains(name)) {
            updateProgressMessage(getString(R.string.already_installed))
            onSuccessfulLoad(name, launch = true)
            return
        }

        // Create request to install a feature module by name.
        val request = SplitInstallRequest.newBuilder()
            .addModule(name)
            .build()

        // Load and install the requested feature module.
        manager.startInstall(request)

        updateProgressMessage(getString(R.string.starting_install_for, name))
    }

    private fun onSuccessfulLoad(moduleName: String, launch: Boolean) {
        if (launch) {
            when (moduleName) {
                moduleChat -> launchActivity(CHAT_CLASSNAME)
            }
        }

        displayButtons()
    }

    /** Request uninstall of all features. */
    private fun requestUninstall() {
        toastAndLog("Requesting uninstall of all modules." +
                "This will happen at some point in the future.")

        val installedModules = manager.installedModules.toList()
        manager.deferredUninstall(installedModules).addOnSuccessListener {
            toastAndLog("Uninstalling $installedModules")
        }.addOnFailureListener {
            toastAndLog("Failed installation of $installedModules")
        }
    }

    private fun updateProgressMessage(message: String) {
        if (binding.progress.visibility != View.VISIBLE) displayProgress()
        binding.progressText.text = message
    }

    /** Launch an activity by its class name. */
    private fun launchActivity(className: String) {
        val intent = Intent().setClassName(BuildConfig.APPLICATION_ID, className)
        startActivity(intent)
    }

    /** Display a loading state to the user. */
    private fun displayLoadingState(state: SplitInstallSessionState, message: String) {
        displayProgress()

        binding.progressBar.max = state.totalBytesToDownload().toInt()
        binding.progressBar.progress = state.bytesDownloaded().toInt()

        updateProgressMessage(message)
    }

    /** Display progress bar and text. */
    private fun displayProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    /** Display buttons to accept user input. */
    private fun displayButtons() {
        binding.progress.visibility = View.GONE
    }
}

fun HomeActivity.toastAndLog(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    Log.d(TAG, text)
}

private const val PACKAGE_NAME = "com.mhendrif.mysimplelogin"
private const val CHAT_CLASSNAME = "$PACKAGE_NAME.chat.ChatActivity"
private const val CONFIRMATION_REQUEST_CODE = 1
private const val TAG = "DynamicFeatures"