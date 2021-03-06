package com.myntai.slightech.myntairobotromupdateservice;

import android.os.Build;

import java.io.DataOutputStream;

public class Common {
    public static final int MODE_AUTOMATIC = 1;//当前升级　不需要人参与
    public static final int MODE_MANUAL = 2;

    public static final int MODE_TYPE_ALL_PKG = 1;//
    public static final int MODE_TYPE_DIFF_PKG = 2;//
    public static final int MODE_TYPE_ALL_PKG_AUTO = 3;//

    public static final int DIALOG_CODE_DOWNLOAD = 3;
    public static final int DIALOG_CODE_TX2_UPGRADE = 4 ;
    public static final String DIALOG_TAG_NEW_VERSION = "dialogTagNewVersion";
    public static final String DIALOG_TAG_TX2_NEW_VERSION = "dialogTx2TagNewVersion";

    public static final int DOWNLOADPKG_TYPE_ALL = 1;
    public static final int DOWNLOADPKG_TYPE_DIFF = 2;
    public static final int DOWNLOADPKG_TYPE_ALL_AUTO = 3;

    public static final int UPGRADE_CODE_DIFF_PKG_MANUAL = 1;//手动　升级全包
    public static final int UPGRADE_CODE_ALL_PKG_MANUAL = 3;
    public static final int UPGRADE_CODE_ALL_PKG_AUTO = 4;//手动　升级全包

    public static final String DownLoadDir = "/androidRomUpdate";
    public static final String DownLoadTX2Dir = "/tx2Update";

    public static final String TX2_SERVICE_IP = "1.2.1.2" ;
    public static final int TX2_SERVICE_PORT = 3333 ;

    public static final String TX2_CMD_UPLOAD = "cmd_upload" ;
    public static final String TX2_CMD_VERSION = "cmd_version" ;

    public static long getFireflyVersionCode() {
        String[] content = Build.DISPLAY.split("\\.");
        if (content.length > 0) {
            return Long.valueOf(content[0]);
        } else {
            return 1;
        }
    }

    public static long getTx2VersionCode() {
        return 0L;
    }

    static private Process process = null;
    static private DataOutputStream dataOutputStream = null;
    public static void shellExec(String command) throws Exception{
        try {
            process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            dataOutputStream.writeBytes(command);
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            process.waitFor();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                process.destroy();
            } catch (Exception e) {
                throw e;
            }
        }
    }
}
