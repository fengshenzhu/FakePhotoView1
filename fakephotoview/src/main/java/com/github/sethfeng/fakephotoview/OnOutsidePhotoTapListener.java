package com.github.sethfeng.fakephotoview;


import com.example.atemktx.photoview.FakeImageView;

/**
 * Callback when the user tapped outside of the photo
 */
public interface OnOutsidePhotoTapListener {

    /**
     * The outside of the photo has been tapped
     */
    void onOutsidePhotoTap(FakeImageView imageView);
}
