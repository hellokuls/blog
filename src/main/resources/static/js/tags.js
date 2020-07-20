//标签颜色随机显示
var labelindex = $(".ui.basic.label.m-tags").length;
var colorList = ["#9dc6eb", "#7fccde", "#b9a3ef", "#fdb1ca", "#6698cb", "#f8c471", "#b9a3ef", "#fdb1ca", "#f0d264", "#fa5a5a", "#82c8a0"];
for (var i = 0; i < labelindex; i++) {
    var bgColor = getColorByRandom(colorList);
    $(".ui.basic.label.m-tags").eq(i).css("background", bgColor);
}

function getColorByRandom(colorList) {
    var colorIndex = Math.floor(Math.random() * colorList.length);
    var color = colorList[colorIndex];
    return color;
}
