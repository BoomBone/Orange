# Orange
[![](https://www.jitpack.io/v/BoomBone/Orange.svg)](https://www.jitpack.io/#BoomBone/Orange)  

**简介：** 对Retrofit2.0的封装  
 
## 集成方式
在Project下面的gradle添加
```
dependencies {
	        compile 'com.github.BoomBone:Orange:1.0.0'
	}
```
在app的gradle添加
```
dependencies {
	        compile 'com.github.BoomBone:Orange:1.0.0'
	}
```
使用方法：
简单的get请求
```
 RestClient.builder()
                .url("https://www.baidu.com")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                       //处理请求成功
                    }
                })
                .build()
                .get();
```

## 请求类型
get post put delete download upload 等 

接口类：
1.ISuccess：请求成功回调成功
2.IError：  请求成功返回状态码不是200
3.IFailure  请求失败
4.IRequest  请求前后可添加操作
[Retrofit2.0的使用详解](http://blog.csdn.net/carson_ho/article/details/73732076)
原始使用方法简单使用说明
- 创建接口例如RestService，创建方法如get()
- Retrofit retrofit = new Retrofit.Builder()....build();
- RestService service = retrofit.create(RestService.class);
- Call<String> call = service.get();
- call.enqueue(CallBack...)

### 引用库
[retrofit](https://github.com/square/retrofit)
[AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView)
