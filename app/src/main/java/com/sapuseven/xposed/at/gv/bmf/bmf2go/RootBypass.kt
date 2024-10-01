package com.sapuseven.xposed.at.gv.oe.app

import android.content.Context
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam


class RootBypass : IXposedHookLoadPackage {
    @Throws(Throwable::class)
    override fun handleLoadPackage(lpparam: LoadPackageParam?) {
        if (lpparam?.packageName != "at.gv.bmf.bmf2go") return

        findAndHookMethod(
            "at.gv.bmf.bmf2go.taxequalization.tools.utils.RootDetection",
            lpparam.classLoader,
            "isRooted",
            Context.class,
            XC_MethodReplacement.returnConstant(false)
        )
    }
}
