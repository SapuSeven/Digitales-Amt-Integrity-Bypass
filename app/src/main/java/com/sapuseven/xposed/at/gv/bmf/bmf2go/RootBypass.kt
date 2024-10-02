package com.sapuseven.xposed.at.gv.bmf.bmf2go

import android.content.Context
import android.util.Log
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
import java.lang.reflect.Member
import java.lang.reflect.Method

class RootBypass : IXposedHookLoadPackage {
    @Throws(Throwable::class)
    override fun handleLoadPackage(lpparam: LoadPackageParam?) {
        if (lpparam?.packageName != "at.gv.bmf.bmf2go") return

        findAndHookMethod(
            "at.gv.bmf.bmf2go.taxequalization.tools.utils.RootDetection",
            lpparam.classLoader,
            "isRooted",
            Context::class.java,
            XC_MethodReplacement.returnConstant(false)
        )

        findAndHookMethod(
            "at.gv.bmf.bmf2go.taxequalization.tools.utils.AttestationHelper",
            lpparam.classLoader,
            "isBootStateOk",
            XC_MethodReplacement.returnConstant(true)
        )
    }
}
