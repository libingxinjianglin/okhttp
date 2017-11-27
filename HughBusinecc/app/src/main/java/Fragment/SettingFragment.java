package Fragment;

import android.view.View;

import drawable.tencent.com.hughbusinecc.R;


/**
 * Created by Administrator on 2017/11/25 0025.
 */

public class SettingFragment extends BaseFragment{
    @Override
    public View getLayoutView() {
        View mView = View.inflate(getContext(), R.layout.fragement_setting_layout ,null);
        return mView;
    }
}
