package com.haocent.android.retrofit2sample;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * 摘自官方文档，Glide 4.x 的问题解决方法
 *
 * Created by Tnno Wu on 2018/01/24.
 */

@GlideModule
public final class MyAppGlideModule extends AppGlideModule {

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
