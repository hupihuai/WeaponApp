package com.weapon.joker.app.mine;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.View;
import android.widget.Toast;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.weapon.joker.app.mine.login.LoginActivity;
import com.weapon.joker.app.stub.share.IShareListener;
import com.weapon.joker.app.stub.share.ShareParams;
import com.weapon.joker.app.stub.share.ShareType;
import com.weapon.joker.lib.middleware.utils.JLog;
import com.weapon.joker.lib.middleware.utils.share.ShareView;

/**
 * class：   WeaponApp
 * author：  xiaweizi
 * date：    2017/9/17 11:01
 * e-mail:   1012126908@qq.com
 * desc:
 */
public class MineViewModel extends MineContact.ViewModel implements IShareListener, IUiListener {

    private ShareView mShareView;

    /**
     * 初始化分享
     */
    public void initShare() {
        ShareParams params = new ShareParams.Builder().setTitle(getContext().getResources().getString(R.string.share_name))
                .setDescription(getContext().getResources().getString(R.string.share_desc))
                .setAppUrl(getContext().getResources().getString(R.string.share_url))
                .setImgUrl(getContext().getResources().getString(R.string.share_img_url))
                .setResId(R.mipmap.ic_launcher_round)
                .build();
        mShareView = new ShareView(getContext(), params, this);
    }

    /**
     * 点击分享按钮
     * @param view
     */
    public void shareOnClick(View view) {
        mShareView.show();
    }

    public void loginOnClick(View view) {

        AppCompatActivity activity = null;
        if (getContext() instanceof AppCompatActivity) {
            activity = (AppCompatActivity) getContext();
        } else {
            return;
        }

        Intent intent = new Intent(getContext(), LoginActivity.class);
        Explode explode = new Explode();
        explode.setDuration(500);

        activity.getWindow().setExitTransition(explode);
        activity.getWindow().setEnterTransition(explode);
        ActivityOptionsCompat sceneTransitionAnimation = ActivityOptionsCompat.makeSceneTransitionAnimation(activity);
        getContext().startActivity(intent, sceneTransitionAnimation.toBundle());
    }

    public IUiListener getIUiListener() {
        return this;
    }


    /*************************** 分享事件相关的回调 ***************************/
    @Override public void onShareSuccess(ShareType shareType, String s) {
        Toast.makeText(getContext(), shareType.toString() + ":\t分享成功", Toast.LENGTH_SHORT).show();
        JLog.i("Share result:\t" + "type:\t" + shareType.toString() + "desc:\t" + s);
    }

    @Override public void onShareFailed(ShareType shareType, String s) {
        Toast.makeText(getContext(), shareType.toString() + ":\t分享失败", Toast.LENGTH_SHORT).show();
        JLog.i("Share result:\t" + "type:\t" + shareType.toString() + "desc:\t" + s);

    }

    @Override public void onShareCancel(ShareType shareType) {
        Toast.makeText(getContext(), shareType.toString() + ":\t分享取消", Toast.LENGTH_SHORT).show();
        JLog.i("Share result:\t" + "type:\t" + shareType.toString());

    }

    @Override public void onComplete(Object o) {JLog.i("QQ Share result:\tonComplete!" + o.toString());}
    @Override public void onError(UiError uiError) {JLog.i("QQ Share result:\tonError!" + uiError.errorMessage);}
    @Override public void onCancel() {JLog.i("QQ Share result:\tonCancel!");}
    /************************************************************************/


}
