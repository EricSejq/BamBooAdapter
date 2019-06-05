package com.example.eric.bambooadapter.fragment;

import androidx.fragment.app.Fragment;

/**
 * Description:
 * Data：2019/4/27-16:49
 *
 * @author: eric
 */
public class FragmentFactory {
    public static Fragment create(String fragmentTag) {
        if (fragmentTag.equals(DiaryFragment.class.toString())) {
            return DiaryFragment.newInstance();
        } else if (fragmentTag.equals(MineFragment.class.toString())) {
            return MineFragment.newInstance();
        } else if (fragmentTag.equals(TipsFragment.class.toString())) {
            return TipsFragment.newInstance();
        } else {
            return null;
        }
    }
}
