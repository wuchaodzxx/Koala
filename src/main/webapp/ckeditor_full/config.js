/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights
 *          reserved. For licensing, see LICENSE.md or
 *          http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	config.uiColor = '#ffffff';
	// 自定义配置

	config.image_previewText = ' '; // 预览区域显示内容
	config.filebrowserImageUploadUrl = "/Koala/imageUpload"; // 要上传的action或servlet
	config.height = 400; // 高度
	config.resize_enabled = false;// 取消 “拖拽以改变尺寸”
	config.enterMode = CKEDITOR.ENTER_BR;
	config.shiftEnterMode = CKEDITOR.ENTER_P;
	config.skin = 'moonocolor';

	config.font_names = '宋体/SimSun;新宋体/NSimSun;仿宋/FangSong;楷体/KaiTi;仿宋_GB2312/FangSong_GB2312;'
			+ '楷体_GB2312/KaiTi_GB2312;黑体/SimHei;华文细黑/STXihei;华文楷体/STKaiti;华文宋体/STSong;华文中宋/STZhongsong;'
			+ '华文仿宋/STFangsong;华文彩云/STCaiyun;华文琥珀/STHupo;华文隶书/STLiti;华文行楷/STXingkai;华文新魏/STXinwei;'
			+ '方正舒体/FZShuTi;方正姚体/FZYaoti;细明体/MingLiU;新细明体/PMingLiU;微软雅黑/Microsoft YaHei;微软正黑/Microsoft JhengHei;'
			+ 'Arial Black/Arial Black;' + config.font_names;
	config.keystrokes = [ [ CKEDITOR.ALT + 121 /* F10 */, 'toolbarFocus' ], // 获取焦点
	[ CKEDITOR.ALT + 122 /* F11 */, 'elementsPathFocus' ], // 元素焦点

	[ CKEDITOR.SHIFT + 121 /* F10 */, 'contextMenu' ], // 文本菜单

	[ CKEDITOR.CTRL + 90 /* Z */, 'undo' ], // 撤销
	[ CKEDITOR.CTRL + 89 /* Y */, 'redo' ], // 重做
	[ CKEDITOR.CTRL + CKEDITOR.SHIFT + 90 /* Z */, 'redo' ], //

	[ CKEDITOR.CTRL + 76 /* L */, 'link' ], // 链接

	[ CKEDITOR.CTRL + 66 /* B */, 'bold' ], // 粗体
	[ CKEDITOR.CTRL + 73 /* I */, 'italic' ], // 斜体
	[ CKEDITOR.CTRL + 85 /* U */, 'underline' ], // 下划线

	[ CKEDITOR.ALT + 109 /*-*/, 'toolbarCollapse' ] ]

};
