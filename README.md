# 用到的几个方法

```
/**
  * oldl, l 滑动前后，x的偏移量
  * oldt, t 滑动前后，y的偏移量
  */
onScrollChanged(int l, int t, int oldl, int oldt)
```

- 自定义view中

```
// 设置子view 以及 自身的宽和高
onMeasure(int widthMeasureSpec, int heightMeasureSpec)
```

```
// 设置子view的放置位置
onLayout(boolean changed, int l, int t, int r, int b)
```

```
// 当xml布局文件夹在完成，会回调此方法
onFinishInflate()
```

```
// 当控件的宽高发生变化时，会调用该方法 获得控件的宽高，或者根据控件的宽高设置某些元素的宽高
onSizeChanged(int w, int h, int oldw, int oldh)
```

```
// 可以绘制图形
dispatchDraw(Canvas canvas)
```




