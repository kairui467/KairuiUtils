package com.ingenic.launcher.other;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 
 * @Description:
 *
 * @author kairuili
 *
 * @date 2016年2月1日 上午10:29:59
 *
 */
public class CircleImageView extends ImageView {

	private Paint paint;
	private Paint uninstallIconPaint;

	public boolean mUserApp;
	/** 是否为卸载状态 */
	public boolean mIsUninState = false;
	public String mAppName;
	public String mPackageName;

	public CircleImageView(Context context) {
		super(context);
		init();
	}

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		paint = new Paint();
		paint.setAntiAlias(true); //防止边缘的锯齿
		paint.setColor(Color.GRAY); //这里的颜色决定了边缘的颜色
		paint.setFilterBitmap(true);

		uninstallIconPaint = new Paint();
		uninstallIconPaint.setColor(Color.RED);
		uninstallIconPaint.setAntiAlias(true);
		uninstallIconPaint.setFilterBitmap(true);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);

		// 在用户App左上角显示卸载图标
		if (mUserApp && mIsUninState) {
			int radius = getWidth() / 6;
			canvas.drawCircle(radius, radius, radius, uninstallIconPaint);
		}
	}

	protected void onDraw(Canvas canvas) {
		Drawable drawable = getDrawable();
		if (null == drawable || getWidth() == 0 || getHeight() == 0)
			return;

		//先类型转换
		Bitmap b = drawableToBitmap(drawable);
		//creates a mutable copy of the image using the option specified.
		Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

		int w = getWidth();
		int h = getHeight();

		//圆形ImageView的半径为布局中的ImageView定义大小
		Bitmap roundBitmap = getCroppedBitmap(bitmap, w);

		canvas.drawARGB(0, 0, 0, 0);
		// 这才开始画圆
		canvas.drawCircle(w / 2, h / 2, w / 2, paint);
		//x0，y0 距离左边为0 ，距离上边为0
		canvas.drawBitmap(roundBitmap, 0, 0, null);
	}

	/**
	 * 获取剪裁的Bitmap
	 * @param bmp
	 * @param radius
	 */
	public Bitmap getCroppedBitmap(Bitmap bmp, int radius) {

		Bitmap sbmp;
		//以宽为标准
		if (bmp.getWidth() != radius || bmp.getHeight() != radius)
			sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
		else
			sbmp = bmp;

		Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(),
				Config.ARGB_8888);

		//output不仅起到设置画布大小的作用，而是在output上画画。最后返回output
		Canvas canvas = new Canvas(output);

		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		//图像的抖动处理,保持好的图片效果
		paint.setDither(true);
		// 把画笔设置为透明
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(Color.WHITE);
		//paint.setStyle(Paint.Style.FILL);画笔默认的style

		//第一个参数为圆心x坐标，二为圆心的y坐标，第三个参数为半径减去的数值为白边的宽度.
		//这里剪下的是图片的半径。
		canvas.drawCircle(sbmp.getWidth() / 2, sbmp.getHeight() / 2,
				sbmp.getWidth() / 2 - 2, paint);
		// 取两层绘制交集。显示前景色。
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

		canvas.drawBitmap(sbmp, rect, rect, paint);

		return output;
	}

	public static Bitmap drawableToBitmap(Drawable drawable) {

		Bitmap bitmap = Bitmap.createBitmap(

		drawable.getIntrinsicWidth(),

		drawable.getIntrinsicHeight(),

		drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

		: Bitmap.Config.RGB_565);

		Canvas canvas = new Canvas(bitmap);
		//canvas.setBitmap(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

}
