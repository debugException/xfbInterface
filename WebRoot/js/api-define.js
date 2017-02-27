/*
 * @(#)api-define.js 1.0.0 14/02/13
 * Copyright 2014© Emagsoftware Technology Co., Ltd. All Rights reserved.
 */
var api_defines = [];
var api_callback = {};
var systemApi = {
    category:"system",
    name:"系统API",
    api:[]
}
var hdcApi = {
    category:"hdc",
    name:"视频相关",
    api:[]
}
var pluginApi = {
    category:"plugin",
    name:"直播",
    api:[]
}
var familyGroupApi = {
    category:"familyGroup",
    name:"搜索",
    api:[]
}
var album = {
    category:"album",
    name:"游戏",
    api:[]
}
var collections = {
	    category:"collections",
	    name:"收藏",
	    api:[]
}
api_defines.push(systemApi);
api_defines.push(hdcApi);
api_defines.push(pluginApi);
api_defines.push(familyGroupApi);
api_defines.push(album);
api_defines.push(collections);

systemApi.api.push( {name:"用户登录", uri:"customer/login.do",needAppId:false,needLogon:false, callback:"loginSuccess",
            fields:[
                {name:"开放平台类型", key:"openType", remark:"1-腾讯QQ互联 2-新浪微博开放平台 3-微信开放平台",required:"*"}, 
                {name:"开放平台ID", key:"openID",required:"*"},
                {name:"开放平台昵称", key:"openNick",required:"*"}
            ]});
systemApi.api.push( {name:"首页", uri:"customer/homePage.do",needAppId:false,needLogon:false, callback:"loginSuccess",
    fields:[
            	{name:"每个分类下的视频数量", key:"videoNumber",remark:"每个分类下的视频数量", defalutValue:"2"},
                {name:"记录开始下标", key:"page",remark:"视频一级分类记录开始下标", defalutValue:"0"},
                {name:"每页显示条数", key:"pageSize",remark:"视频一级分类每页显示条数", defalutValue:"6"}
    ]});
systemApi.api.push( {name:"返回所有配置信息", uri:"appSys/getSysConfig.do",needAppId:false,needLogon:false, callback:"loginSuccess",
    fields:[
    ]});
systemApi.api.push( {name:"反馈建议", uri:"suggestion/addSuggestion.do",needAppId:false,needLogon:false, callback:"loginSuccess",
    fields:[
        {name:"用户手机号", key:"customerMobile", remark:"用户手机号"},
        {name:"反馈内容", key:"content",remark:"反馈内容",required:"*"}
    ]});
 systemApi.api.push( {name:"爱看游戏应用的最新详细信息", uri:"appSys/getNewestApp.do",needAppId:false,needLogon:false, callback:"loginSuccess",
    fields:[
    ]});
 systemApi.api.push({name:"app心跳接口", uri:"appSys/appHeartbeat.do",needLogon:true,isHdcApi:false,
	    fields:[
	        {name:"心跳会话key", key:"uuid",remark:"心跳会话UUID,如果为空，则认为是第一次调用，接口会返回UUID"}
	    ]});

 /*
systemApi.api.push( {name:"系统消息查询", uri:"api/systemMessageList",needAppId:false,needLogon:false, callback:"loginSuccess",
    fields:[
    ]});

	    hdcApi.api.push({name:"查询视频详情", uri:"video/currentPlayVideo.do",needLogon:true,isHdcApi:false,
    fields:[
        {name:"视频ID", key:"videoID",remark:"视频ID", required:"*"}
    ]});
  */
 hdcApi.api.push({name:"视频点播页", uri:"video/videoPlayPage.do",needLogon:true,isHdcApi:false,
	    fields:[
	        {name:"视频ID", key:"videoID",remark:"视频ID", required:"*"},
	        {name:"分辨率", key:"quality",remark:"分辨率，SD:标清，HD:超清，可为空，默认为SD，返回结果：按参数找不到，取默认分辨率，默认分辨率没取到，则随机取"}
	    ]});
 hdcApi.api.push({name:"查询视频播放地址", uri:"video/videoPlayUrl.do",needLogon:true,isHdcApi:false,
	 fields:[
	         {name:"视频ID", key:"videoID",remark:"视频ID", required:"*"},
	         {name:"分辨率", key:"quality",remark:"分辨率，SD:标清，HD:超清，可为空，默认为SD，返回结果：按参数找不到，取默认分辨率，默认分辨率没取到，则随机取"},
	         {name:"视频来源平台", key:"originType",remark:"视频来源平台 1：天翼 2：斗鱼（暂时只有天翼）", required:"*"},
	         {name:"视频第三方ID", key:"contentID",remark:"视频第三方ID", required:"*"}
	         ]});
hdcApi.api.push( {name:"根据分页返回专辑", uri:"album/albumList.do",needLogon:true,isHdcApi:false,
            fields:[
                {name:"记录开始下标", key:"page",remark:"记录开始下标", defalutValue:"0"},
                {name:"每页显示条数", key:"pageSize",remark:"每页显示条数", defalutValue:"6"}
            ]});
hdcApi.api.push( {name:"分页返回视频一级分类(包含视频)", uri:"videoType/videoTypeList.do",needLogon:true,isHdcApi:true,
            fields:[
                {name:"每个分类下的视频数量", key:"videoNumber",remark:"每个分类下的视频数量", defalutValue:"2"},
                {name:"记录开始下标", key:"page",remark:"记录开始下标", defalutValue:"0"},
                {name:"每页显示条数", key:"pageSize",remark:"每页显示条数", defalutValue:"6"}
            ]});

hdcApi.api.push({name:"查询视频列表", uri:"video/videoList.do",needLogon:true,isHdcApi:false,
    fields:[
        {name:"记录开始下标", key:"page",remark:"记录开始下标", defalutValue:"0"},
        {name:"每页显示条数", key:"pageSize",remark:"每页显示条数", defalutValue:"6"},
        {name:"视频关联类型", key:"type",remark:"1 : 游戏，2：专辑, 3 : 视频一级分类",required:"*"},
        {name:"视频关联类型ID", key:"originID",remark:"视频关联类型ID", required:"*"}
    ]});


/*hdcApi.api.push({name:"返回视频点击量最多的游戏名称(用于导航显示)", uri:"game/gameHot.do",needLogon:true,isHdcApi:false,
    fields:[
    ]});*/

hdcApi.api.push({name:"未观看视频数量", uri:"game/gameNewNum.do",needLogon:true,isHdcApi:false,
    fields:[
        {name:"游戏ID", key:"gameID",remark:"需要查询视频数量的游戏ID",required:"*"},
        {name:"最后观看时间", key:"lastDate",remark:"最后一次观看该游戏中视频的时间，格式：2014-11-11 11:11:11"}
    ]});


pluginApi.api.push({name:"当前观看人数最多的直播流", uri:"live/liveHot.do",needLogon:true,isHdcApi:false,
    fields:[
    ]});

pluginApi.api.push({name:"直播流列表", uri:"live/liveList.do",needLogon:true,isHdcApi:false,
    fields:[
        {name:"记录开始下标", key:"page",remark:"记录开始下标", defalutValue:"0"},
		{name:"每页显示条数", key:"pageSize",remark:"每页显示条数", defalutValue:"6"},
		{name:"每个直播下展现的节目单数量", key:"scheduleNumber",remark:"每个直播下展现的节目单数量，从当前时间的节目单开始", defalutValue:"2"}
    ]});
pluginApi.api.push({name:"直播播放页", uri:"live/livePlayPage.do",needLogon:true,isHdcApi:false,
    fields:[
        {name:"直播ID", key:"liveID",remark:"播放的直播ID"},
        {name:"记录开始下标", key:"page",remark:"评论记录开始下标", defalutValue:"0"},
        {name:"分辨率", key:"quality",remark:"分辨率，SD:标清，HD:超清，可为空，默认为SD，返回结果：按参数找不到，取默认分辨率，默认分辨率没取到，则随机取"},
		{name:"每页显示条数", key:"pageSize",remark:"每页显示评论条数", defalutValue:"6"}
    ]});
pluginApi.api.push({name:"直播心跳接口", uri:"live/liveHeartbeat.do",needLogon:true,isHdcApi:false,
    fields:[
        {name:"直播ID", key:"liveID",remark:"播放的直播ID",requeird:"*"},
        {name:"直播心跳会话key", key:"uuid",remark:"直播心跳会话key",requeird:"*"}
    ]});
pluginApi.api.push({name:"获取直播播放地址", uri:"live/livePlayUrl.do",needLogon:true,isHdcApi:false,
    fields:[
        {name:"直播ID", key:"liveID",remark:"播放的直播ID"},
        {name:"分辨率", key:"quality",remark:"分辨率，SD:标清，HD:超清，可为空，默认为SD，返回结果：按参数找不到，取默认分辨率，默认分辨率没取到，则随机取"}
    ]});
pluginApi.api.push({name:"插入用户直播评论", uri:"live/addLiveComment.do",needLogon:true,isHdcApi:false,
    fields:[ 
        {name:"直播ID", key:"liveID",remark:"评论的直播ID",requeird:"*"},
        {name:"评论",key:"comment",remark:"评论",requeird:"*"}
    ]});

pluginApi.api.push({name:"查询用户直播评论", uri:"live/searchForComment.do",needLogon:true,isHdcApi:false,
    fields:[
        {name:"直播ID", key:"liveID",remark:"要查询的当前直播的ID",requeird:"*"},
        {name:"查询时间", key:"searchTime",remark:"客户端传查询时间,格式为2014-11-18 22:22:22",requeird:"*"},
        {name:"查询条数",key:"pageSize",remark:"一次查询多少值，可写死",defalutValue:"6"},
        {name:"查询起点",key:"page",remark:"从第几条开始查",defalutValue:"0"}
    ]});

familyGroupApi.api.push({name:"搜索视频、游戏", uri:"search/searchGameVideo.do",needLogon:true,isHdcApi:false,
    fields:[
        {name:"记录开始下标", key:"page",remark:"记录开始下标", defalutValue:"0"},
		{name:"每页显示条数", key:"pageSize",remark:"每页显示条数", defalutValue:"6"},
		{name:"搜索关键词", key:"searchTerm",remark:"搜索关键词",required:"*"}
    ]});

familyGroupApi.api.push({name:"获取搜索热门词", uri:"search/searchTermList.do",needLogon:true,isHdcApi:false,
    fields:[
    ]});

album.api.push({name:"返回游戏列表", uri:"game/gameList.do",needLogon:true,isHdcApi:false,
    fields:[
		{name:"记录开始下标", key:"page",remark:"记录开始下标", defalutValue:"0"},
		{name:"每页显示条数", key:"pageSize",remark:"每页显示条数", defalutValue:"6"}
    ]});


album.api.push({name:"返回指定游戏的最新APP信息", uri:"game/gameAppListByGameIDs.do",needLogon:true,isHdcApi:false,
    fields:[
		{name:"记录开始下标", key:"page",remark:"记录开始下标", defalutValue:"0"},
		{name:"每页显示条数", key:"pageSize",remark:"每页显示条数", defalutValue:"6"},
//		{name:"手机平台类型", key:"type",remark:"1：android; 2：ios",required:"*"},
        {name:"游戏ID数组", key:"packages",remark:"游戏包名数组，分号分隔",required:"*"}
    ]});
/*album.api.push({name:"查询游戏详情", uri:"game/gameAppInfoByGameID.do",needLogon:true,isHdcApi:false,
    fields:[
		{name:"手机平台类型", key:"appType",remark:"1：android; 2：ios"},
        {name:"游戏ID", key:"gameID",remark:"游戏ID隔"}
    ]});
*/

collections.api.push({name:"添加收藏", uri:"collections/addCollections.do",needLogon:true,isHdcApi:false,
    fields:[
		{name:"视频或直播ID", key:"videoOrliveID",remark:"要收藏的视频或直播ID", required:"*"},
		{name:"收藏类型", key:"type",remark:"1：视频:2：直播", required:"*"},
    ]});

collections.api.push({name:"删除收藏", uri:"collections/deleteCollections.do",needLogon:true,isHdcApi:false,
    fields:[
	{name:"视频或直播ID", key:"videoOrliveID",remark:"要收藏的视频或直播ID", required:"*"},
	{name:"收藏类型", key:"type",remark:"1：视频:2：直播", required:"*"},
    ]});

collections.api.push({name:"查询收藏", uri:"collections/queryCollections.do",needLogon:true,isHdcApi:false,
    fields:[
		{name:"记录开始下标", key:"page",remark:"记录开始下标", defalutValue:"0"},
		{name:"每页显示条数", key:"pageSize",remark:"每页显示条数", defalutValue:"6"},
    ]});