# OMVP
> 简单的mvp框架

## 使用步骤

1. 需要使用MVP框架的Activity继承org.diordna.omvp.BaseActivity，添加匹配的继承自BaseView泛型和继承自BasePresenter泛型
2. 实现方法getView()和getPresenter()
3. 在使用Presenter时，调用getRealPresenter()方法

## 其他功能

### Presenter层注解预处理

Presenter层在处理事件的时候，经常性的需要检查是否绑定View层，或者其他的预检查，例如是否登录。框架增加对这些预处理的支持。  

框架默认增加对View绑定的检查，需要在需要检查的方法上增加@ViewRequire(isBind = true)，则在执行该方法时会自动判断是否绑定，如果返回false，会停止方法执行。

### 自定义注解预处理

创建一个自定义注解，然后创建一个自定义class继承org.diordna.omvp.presenter.chain.Chain，实现下面的方法：

``` java
boolean enable(BasePresenter presenter, Annotation anno);
void failMsg(Context context);
```

然后在Activity层实现方法getPresenter()时，调用Presenter层的addChain(Chain chain)方法，将创建的自定义Chain添加到Presenter层。