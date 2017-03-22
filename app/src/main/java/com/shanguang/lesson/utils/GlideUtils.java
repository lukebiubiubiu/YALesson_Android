package com.shanguang.lesson.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

/**
 * 创建人：${刘泽旻}
 * 创建时间：2016/12/2 11:09
 * 修改备注：glide图片封装类
 */
public class GlideUtils {
        //简单加载图片
        public  static  void loadImg(Context context, String imgUrl, ImageView imageView){
            Glide.with(context).load(imgUrl).into(imageView);
        }
//        //加载图片失败图
//        public  static  void loadImgError(Context context, String imgUrl, ImageView imageView){
//            Glide.with(context).load(imgUrl).placeholder(R.drawable.pic_erro).error(R.drawable.pic_erro).into(imageView);
//        }
        //设置跳过内存缓存
        public  static  void loadImgMemoryCache(Context context, String imgUrl, ImageView imageView){
            Glide.with(context).load(imgUrl).skipMemoryCache(true).into(imageView);
        }
        //设置优先级
        public  static  void loadImgPriority(Activity context, String imgUrl, ImageView imageView){
            Glide.with(context).load(imgUrl).priority(Priority.NORMAL).into(imageView);
        }
//        //设置加载动画
//        public  static  void loadImgAnimate(Activity context, String imgUrl, ImageView imageView){
//            Glide.with(context).load(imgUrl).animate(R.anim.item_alpha_in).into(imageView);
//        }
        //缩略图加载
        public  static  void loadImgThumbnail(Activity context, String imgUrl, ImageView imageView){
            Glide.with(context).load(imgUrl).thumbnail(0.1f).into(imageView);
        }
        //设置加载尺寸
        public  static  void loadImgOverride(Activity context, String imgUrl, ImageView imageView, int width, int height){
            Glide.with(context).load(imgUrl).override(width, height).into(imageView);
        }
        //设置动态GIF加载方式
        //静态
        public  static  void loadImgGIFbitmap(Activity context, String imgUrl, ImageView imageView){
            Glide.with(context).load(imgUrl).asBitmap().into(imageView);
        }
        //动态
        public  static  void loadImgGIF(Activity context, String imgUrl, ImageView imageView){
            Glide.with(context).load(imgUrl).asGif().into(imageView);
        }
        //缓存清理
        //清理磁盘缓存 需要在子线程中执行
        public  static  void loadDiskCache(Activity context){
            Glide.get(context).clearDiskCache();
        }
        //清理内存缓存  可以在UI主线程中进行
        public  static  void loadMemory(Activity context){
            Glide.get(context).clearMemory();
        }
}