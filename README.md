# Orange
对Retrofit的二次封装
[Retrofit2.0的使用详解](http://blog.csdn.net/carson_ho/article/details/73732076)
原始使用方法简单使用说明
- 创建接口例如RestService，创建方法如get()
- Retrofit retrofit = new Retrofit.Builder()....build();
- RestService service = retrofit.create(RestService.class);
- Call<String> call = service.get();
- call.enqueue(CallBack...)
****
## 包含的类
RestClient
***
RestClientBuilder
***
RestCreator
***
RestService
***
接口类：
1.ISuccess
2.IError
3.IFailure
4.IRequest

