package com.sapuseven.xposed.at.gv.oe.app

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam


class RootBypass : IXposedHookLoadPackage {
    @Throws(Throwable::class)
    override fun handleLoadPackage(lpparam: LoadPackageParam?) {
        if (lpparam?.packageName != "at.gv.oe.app") return

        findAndHookMethod(
            "at.asitplus.utils.deviceintegrity.DeviceIntegrityCheck",
            lpparam.classLoader,
            "checkIntegrity",
            object : XC_MethodReplacement() {
                @Throws(Throwable::class)
                override fun replaceHookedMethod(param: MethodHookParam) {
                    param.result = null
                }
            })
    }
}