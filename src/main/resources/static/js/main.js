'use strict'
var sideBarIsHide = false;
var ManuelSideBarIsHide = false;
var ManuelSideBarIsState = false;
$(".openbtn").on("click", function () {
    ManuelSideBarIsHide = true;
    if (!ManuelSideBarIsState) {
        resizeSidebar("1");
        ManuelSideBarIsState = true;
    } else {
        resizeSidebar("0");
        ManuelSideBarIsState = false;
    }
});


$(window).resize(function () {
    if (ManuelSideBarIsHide == false) {
        if ($(window).width() <= 767) {
            if (!sideBarIsHide) ;
            {
                resizeSidebar("1");
                sideBarIsHide = true;
                $(".colhidden").addClass("displaynone");

            }
        } else {
            if (sideBarIsHide) ;
            {
                resizeSidebar("0");
                sideBarIsHide = false;

                $(".colhidden").removeClass("displaynone");

            }
        }
    }
});

function resizeSidebar(op) {

    if (op == "1") {

        $(".ui.sidebar.left").addClass("very thin icon");
        $(".navslide").addClass("marginlefting");
        $(".sidebar.left span").addClass("displaynone");
        $(".sidebar .accordion").addClass("displaynone");
        $(".ui.dropdown.item.displaynone").addClass("displayblock");
        $($(".logo h2")[0]).addClass("displaynone");
        $($(".logo h2")[1]).removeClass("displaynone");
        $(".hiddenCollapse").addClass("displaynone");


    } else {

        $(".ui.sidebar.left").removeClass("very thin icon");
        $(".navslide").removeClass("marginlefting");
        $(".sidebar.left span").removeClass("displaynone");
        $(".sidebar .accordion").removeClass("displaynone");
        $(".ui.dropdown.item.displaynone").removeClass("displayblock");
        $($(".logo h2")[1]).addClass("displaynone");
        $($(".logo h2")[0]).removeClass("displaynone");
        $(".hiddenCollapse").removeClass("displaynone");


    }

}


// using context
$('.ui.right.sidebar')
    .sidebar({
        context: $('#contextWrap .pusher'),
        transition: 'slide out',
        silent: true
    })
    .sidebar('attach events', '.rightsidebar');


function toggleFullScreen(elem) {
    // ## The below if statement seems to work better ## if ((document.fullScreenElement && document.fullScreenElement !== null) || (document.msfullscreenElement && document.msfullscreenElement !== null) || (!document.mozFullScreen && !document.webkitIsFullScreen)) {
    if ((document.fullScreenElement !== undefined && document.fullScreenElement === null) || (document.msFullscreenElement !== undefined && document.msFullscreenElement === null) || (document.mozFullScreen !== undefined && !document.mozFullScreen) || (document.webkitIsFullScreen !== undefined && !document.webkitIsFullScreen)) {
        if (elem.requestFullScreen) {
            elem.requestFullScreen();
        } else if (elem.mozRequestFullScreen) {
            elem.mozRequestFullScreen();
        } else if (elem.webkitRequestFullScreen) {
            elem.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
        } else if (elem.msRequestFullscreen) {
            elem.msRequestFullscreen();
        }
    } else {
        if (document.cancelFullScreen) {
            document.cancelFullScreen();
        } else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        } else if (document.webkitCancelFullScreen) {
            document.webkitCancelFullScreen();
        } else if (document.msExitFullscreen) {
            document.msExitFullscreen();
        }
    }
}

$(".ui.dropdown").dropdown({
    allowCategorySelection: true,
    transition: "fade up"
});
$('.ui.accordion').accordion('open', this.index);


// 本地缓存处理
var storage = {
    set: function (key, value) {
        window.localStorage.setItem(key, value);
    },
    get: function (key) {
        return window.localStorage.getItem(key);
    },
    remove: function (key) {
        window.localStorage.removeItem(key);
    },
    clear: function () {
        window.localStorage.clear();
    }
};


// history（表示去掉地址的#）否则地址以"#"形式展示
var mode = "history";

/**
 iframe相关
 */

//通过遍历给菜单项加上data-index属性
$(".text-sidebar").each(function (index) {
    if (!$(this).attr('data-index')) {
        $(this).attr('data-index', index);
    }
});

function setIframeUrl(href) {
    if (mode == "history") {
        storage.set('publicPath', href);
    } else {
        var nowUrl = window.location.href;
        var newUrl = nowUrl.substring(0, nowUrl.indexOf("#"));
        window.location.href = newUrl + "#" + href;
    }
}

//计算元素集合的总宽度
function calSumWidth(elements) {
    var width = 0;
    $(elements).each(function () {
        width += $(this).outerWidth(true);
    });
    return width;
}

//滚动到指定选项卡
function scrollToTab(element) {
    var marginLeftVal = calSumWidth($(element).prevAll()),
        marginRightVal = calSumWidth($(element).nextAll());
    // 可视区域非tab宽度
    var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
    //可视区域tab宽度
    var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
    //实际滚动宽度
    var scrollVal = 0;
    if ($(".page-tabs-content").outerWidth() < visibleWidth) {
        scrollVal = 0;
    } else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
        if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
            scrollVal = marginLeftVal;
            var tabElement = element;
            while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
                scrollVal -= $(tabElement).prev().outerWidth();
                tabElement = $(tabElement).prev();
            }
        }
    } else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
        scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
    }
    $('.page-tabs-content').animate({
            marginLeft: 0 - scrollVal + 'px'
        },
        "fast");
}

/**
 * 点击选项卡-菜单
 */
function activeTab() {

    if (!$(this).hasClass('active')) {
        var currentId = $(this).data('id');
        // 显示tab对应的内容区
        $('iframe').each(function () {
            console.log($(this).data('id'))
            if ($(this).data('id') == currentId) {
                $(this).show().siblings('iframe').hide();
                return false;
            }
        });

        $('.text-sidebar').each(function () {
            if ($(this).attr("href") == currentId) {
                $(this).addClass("active").siblings("a").removeClass("active");
            }
        })
        $(this).addClass('active').siblings('.menuTab').removeClass('active');
        scrollToTab(this);
    }
}

// 点击选项卡菜单
$('.menuTabs').on('click', '.menuTab', activeTab);


/**
 * 点击sidebar后，菜单栏的变化
 * @param this_
 * @returns {boolean}
 */
function menuItem(this_) {
    // 获取标识数据
    var dataUrl = this_.attr('href'),
        dataIndex = this_.data('index'),
        menuName = $.trim(this_.text()),
        flag = true;
    setIframeUrl(this_.attr("href"));

    if (dataUrl == undefined || $.trim(dataUrl).length == 0) return false;

    // 选项卡菜单已存在
    $('.menuTab').each(function () {

        if ($(this).data('id') == dataUrl) {

            if (!$(this).hasClass('active')) {

                $(this).addClass('active').siblings('.menuTab').removeClass('active');
                scrollToTab(this);
                // 显示tab对应的内容区
                $('iframe').each(function () {
                    if ($(this).data('id') == dataUrl) {
                        $(this).show().siblings('iframe').hide();
                        return false;
                    }
                });
            }
            flag = false;
            return false;
        }
    });
    // 选项卡菜单不存在
    if (flag) {
        var str = '<a href="javascript:;" class="ui label active m-text-tabs menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
        $('.menuTab').removeClass('active');

        // 添加选项卡对应的iframe
        var str1 = '<iframe class="iframeMain" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';

        $('.mainWrap').find('iframe').hide().parents('.mainWrap').append(str1);

        $('#loaddimmer').removeClass("disabled").addClass("active");

        $('.mainWrap iframe:visible').on("load", function () {
            $('#loaddimmer').removeClass("active").addClass("disabled");
            iframe_height($(this))
        });

        // 添加选项卡
        $('.menuTabs .page-tabs-content').append(str);
        scrollToTab($('.menuTab .active'));
    }
    return false;
}

/**
 * iframe高度
 * @param _this
 */
function iframe_height(_this) {
    //长度
    var iframe_len = document.getElementsByClassName("iframeMain").length;

    var frameObj = document.getElementsByClassName("iframeMain")[iframe_len - 1].contentDocument || document.getElementsByClassName("iframeMain")[iframe_len - 1].contentWindow.document,
        mainWrapHeight = $(window).height() - window.document.getElementById("nav").offsetHeight,
        frame_height = frameObj.documentElement.scrollHeight;
    console.log(mainWrapHeight);
    // console.log(frame_height);

    _this.height(mainWrapHeight)
}


$(".iframeMain").on("load", function () {//iframe加载完后 高度自适应。

    iframe_height($(this))
    // console.log("这里的url为："+$(this).data('id'))
});



$('#menuList').on('click', 'a', function (e) {

    $(this).addClass("active").siblings("a").removeClass("active");
    e.preventDefault();
    // console.log($(this).attr("href"));
    // $(".iframeMain").attr("src",$(this).attr("href"));
    var this_ = $(this);
    menuItem(this_);
});


/**
 * 关闭选项卡菜单
 * @returns {boolean}
 */
function closeTab() {
    var closeTabId = $(this).parents('.menuTab').data('id');
    var currentWidth = $(this).parents('.menuTab').width();
    var panelUrl = $(this).parents('.menuTab').data('panel');

    // 当前元素处于活动状态
    if ($(this).parents('.menuTab').hasClass('active')) {
        // 当前元素后面有同辈元素，使后面的一个元素处于活动状态
        if ($(this).parents('.menuTab').next('.menuTab').length) {

            var activeId = $(this).parents('.menuTab').next('.menuTab:eq(0)').data('id');
            $(this).parents('.menuTab').next('.menuTab:eq(0)').addClass('active');

            $('iframe').each(function () {
                if ($(this).data('id') == activeId) {
                    $(this).show().siblings('iframe').hide();
                    return false;
                }
            });

            var marginLeftVal = parseInt($('.page-tabs-content').css('margin-left'));
            if (marginLeftVal < 0) {
                $('.page-tabs-content').animate({
                        marginLeft: (marginLeftVal + currentWidth) + 'px'
                    },
                    "fast");
            }

            //  移除当前选项卡
            $(this).parents('.menuTab').remove();
            // sidebar滚动到指定位置
            $('.text-sidebar').each(function () {
                if ($(this).attr("href") == activeId) {
                    $(this).addClass("active").siblings("a").removeClass("active");
                }
            })
            // 移除tab对应的内容区
            $('iframe').each(function () {

                if ($(this).data('id') == closeTabId) {

                    $(this).remove();
                    return false;
                }
            });
        }

        // 当前元素后面没有同辈元素，使当前元素的上一个元素处于活动状态
        if ($(this).parents('.menuTab').prev('.menuTab').length) {
            var activeId = $(this).parents('.menuTab').prev('.menuTab:last').data('id');
            $(this).parents('.menuTab').prev('.menuTab:last').addClass('active');
            $('iframe').each(function () {
                if ($(this).data('id') == activeId) {
                    $(this).show().siblings('iframe').hide();
                    return false;
                }
            });

            //  移除当前选项卡
            $(this).parents('.menuTab').remove();

            // sidebar滚动到指定位置
            $('.text-sidebar').each(function () {
                if ($(this).attr("href") == activeId) {
                    $(this).addClass("active").siblings("a").removeClass("active");
                }
            })

            // 移除tab对应的内容区
            $('iframe').each(function () {
                if ($(this).data('id') == closeTabId) {
                    $(this).remove();
                    return false;
                }
            });

            if (panelUrl != null) {
                $('.menuTab[data-id="' + panelUrl + '"]').addClass('active').siblings('.menuTab').removeClass('active');
                $('iframe').each(function () {
                    if ($(this).data('id') == panelUrl) {
                        $(this).show().siblings('iframe').hide();
                        return false;
                    }
                });
            }
        }
    }
    // 当前元素不处于活动状态
    else {
        //  移除当前选项卡
        $(this).parents('.menuTab').remove();

        // sidebar滚动到指定位置
        $('.text-sidebar').each(function () {
            if ($(this).attr("href") == activeId) {
                $(this).addClass("active").siblings("a").removeClass("active");
            }
        })

        // 移除相应tab对应的内容区
        $('iframe').each(function () {
            if ($(this).data('id') == closeTabId) {
                $(this).remove();
                return false;
            }
        });
    }
    scrollToTab($('.menuTab.active'));
    setIframeUrl($('.page-tabs-content').find('.active').attr('data-id'));
    return false;
}

$('.menuTabs').on('click', '.menuTab i', closeTab);


/**
 * 查看左侧隐藏的选项卡
 * @returns {boolean}
 */
function scrollTabLeft() {
    var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
    // 可视区域非tab宽度
    var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
    //可视区域tab宽度
    var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
    //实际滚动宽度
    var scrollVal = 0;
    if (($(".page-tabs-content").width()) < visibleWidth) {
        return false;
    } else {
        var tabElement = $(".menuTab:first");
        var offsetVal = 0;
        while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) { //找到离当前tab最近的元素
            offsetVal += $(tabElement).outerWidth(true);
            tabElement = $(tabElement).next();
        }
        offsetVal = 0;
        if (calSumWidth($(tabElement).prevAll()) > visibleWidth) {
            while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).prev();
            }
            scrollVal = calSumWidth($(tabElement).prevAll());
        }
    }
    $('.page-tabs-content').animate({
            marginLeft: 0 - scrollVal + 'px'
        },
        "fast");
}

/**
 * 查看右侧隐藏的选项卡
 * @returns {boolean}
 */
function scrollTabRight() {
    var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
    // 可视区域非tab宽度
    var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
    //可视区域tab宽度
    var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
    //实际滚动宽度
    var scrollVal = 0;
    if ($(".page-tabs-content").width() < visibleWidth) {
        return false;
    } else {
        var tabElement = $(".menuTab:first");
        var offsetVal = 0;
        while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) { //找到离当前tab最近的元素
            offsetVal += $(tabElement).outerWidth(true);
            tabElement = $(tabElement).next();
        }
        offsetVal = 0;
        while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
            offsetVal += $(tabElement).outerWidth(true);
            tabElement = $(tabElement).next();
        }
        scrollVal = calSumWidth($(tabElement).prevAll());
        if (scrollVal > 0) {
            $('.page-tabs-content').animate({
                    marginLeft: 0 - scrollVal + 'px'
                },
                "fast");
        }
    }
}


// 左移按扭
$('.tabLeft').on('click', scrollTabLeft);

// 右移按扭
$('.tabRight').on('click', scrollTabRight);


/**
 * 刷新iframe
 */
function refreshTab() {
    var currentId = $('.page-tabs-content').find('.active').attr('data-id');
    var target = $('.iframeMain[data-id="' + currentId + '"]');
    console.log(target)
    var url = target.attr('src');
    target.attr('src', url).ready();
}

// 页签刷新按钮
$('.tabReload').on('click', refreshTab);
