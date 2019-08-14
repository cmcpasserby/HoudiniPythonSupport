package flavors

import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.vfs.VirtualFile
import com.jetbrains.python.sdk.flavors.PythonFlavorProvider
import com.jetbrains.python.sdk.flavors.PythonSdkFlavor
import icons.PythonIcons
import java.io.File
import javax.swing.Icon

class HoudiniSdkFlavor private constructor() : PythonSdkFlavor() {
    override fun isValidSdkHome(path: String?): Boolean {
        val file = File(path)
        return file.isFile && isValidSdkPath(file)
    }

    override fun isValidSdkPath(file: File): Boolean {
        val name = FileUtil.getNameWithoutExtension(file).toLowerCase()
        return name.startsWith("hython")
    }

    override fun getVersionOption(): String {
        return "--version"
    }

    override fun getName(): String {
        return "hython"
    }

    override fun getIcon(): Icon {
        return PythonIcons.Python.Python
    }

    override fun getSdkPath(path: VirtualFile): VirtualFile? {
//        if (isHoudiniFolder(File(path.path))) {
//            return path.findFileByRelativePath("Contents/bin/hython")
//        }
        return path
    }

    companion object {
        val INSTANCE: HoudiniSdkFlavor = HoudiniSdkFlavor()
    }
}

class MayaFlavorProvider : PythonFlavorProvider {
    override fun getFlavor(platformIndependent: Boolean): PythonSdkFlavor? = HoudiniSdkFlavor.INSTANCE
}
