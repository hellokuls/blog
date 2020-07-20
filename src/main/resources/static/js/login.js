//如果iframe中的session过期，跳转至主页面
if (window != top) {
    top.location.href = location.href;
}