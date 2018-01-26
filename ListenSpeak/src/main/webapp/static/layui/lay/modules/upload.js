/** layui-v1.0.7 LGPL License By http://www.layui.com */
;
layui.define("layer",function(e) {
  "use strict";
  var a = layui.jquery,
  t = layui.layer,
  i = (layui.device(), "layui-upload-enter"),
  n = "layui-upload-iframe",
  r = {
    icon: 2,
    shift: 6
  },
  o = {
    file: "文件",
    video: "视频",
    audio: "音频"
  },
  s = function(e) {
    this.options = e
  };
  s.prototype.init = function() {
    var e = this,
    t = e.options,
    r = a("body"),
    s = a(t.elem || ".layui-upload-file"),
    u = a('<iframe id="' + n + '" class="' + n + '" name="' + n + '"></iframe>');
    return a("#" + n)[0] || r.append(u),
    s.each(function(r, s) {
      s = a(s);
      var u = '<form target="' + n + '" method="' + (t.method || "post") + '" key="set-mine" enctype="multipart/form-data" action="' + (t.url || "") + '"></form>',
      l = s.attr("lay-type") || t.type;
      t.unwrap || (u = '<div class="layui-box layui-upload-button">' + u + '<span class="layui-upload-icon"><i class="layui-icon">&#xe608;</i>' + (s.attr("lay-title") || t.title || "上传" + (o[l] || "图片")) + "</span></div>"),
      u = a(u),
      t.unwrap || u.on("dragover",
      function(e) {
        e.preventDefault(),
        a(this).addClass(i)
      }).on("dragleave",
      function() {
        a(this).removeClass(i)
      }).on("drop",
      function() {
        a(this).removeClass(i)
      }),
      s.parent("form").attr("target") === n && (t.unwrap ? s.unwrap() : (s.parent().next().remove(), s.unwrap().unwrap())),
      s.wrap(u)/*,
      s.off("change").on("change",
      function() {
        e.action(this, l)
      })*/
	  s.off("change").on("change",
		function() {
		  t.change&&t.change(s[0]);
		  if(!!t.isAuto){
			  e.action([this], l,t.data);
		  }
		})
    })
  },
  s.prototype.action = function(files, j,data,callback) {
	var o = this,
    s = o.options;
	var form =$(files[0]).parent(); 
	form.html("");
	for (var i = 0; i < files.length; i++) {
		var u = files[i].value;
		var l = a(files[i]);
		var p = l.attr("lay-ext") || s.ext || "";
		if (u) {
		      switch (j) {
		      case "file":
		        if (p && !RegExp("\\w\\.(" + p + ")$", "i").test(escape(u))) return t.msg("不支持该文件格式", r),
		        files[i].value = "";
		        break;
		      case "video":
		        if (!RegExp("\\w\\.(" + (p || "avi|mp4|wma|rmvb|rm|flash|3gp|flv") + ")$", "i").test(escape(u))) return t.msg("不支持该视频格式", r),
		        files[i].value = "";
		        break;
		      case "audio":
		        if (!RegExp("\\w\\.(" + (p || "mp3|wav|mid") + ")$", "i").test(escape(u))) return t.msg("不支持该音频格式", r),
		        files[i].value = "";
		        break;
		      default:
		        if (!RegExp("\\w\\.(" + (p || "jpg|png|gif|bmp|jpeg") + ")$", "i").test(escape(u))) return t.msg("不支持该图片格式", r),
		        files[i].value = "";
		      }
		      s.before && s.before(files[i]);
		      //l.parent().submit();
		      form.append(files[i]);
		}
	} 
	var formData = new FormData(form[0]);
	for(var i in data){
		formData.append(i,data[i]);
	}
	/*alert(s.before());
	return ;*/
	"function" == typeof callback && callback(formData);
	var c = a("#" + n),
      f = setInterval(function() {
        var a;
        try {
          a = c.contents().find("body").text()
        } catch(i) {
          t.msg("上传接口存在跨域", r),
          clearInterval(f)
        }
        if (a) {
          clearInterval(f),
          c.contents().find("body").html("");
          try {
            a = JSON.parse(a)
          } catch(i) {
            return a = {},
            t.msg("请对上传接口返回JSON字符", r)
          }
          "function" == typeof s.success && s.success(a, e)
        }
      },
      30);
  },
  e("upload",
  function(e) {
    var a = new s(e = e || {});
    a.init();
    return a;
  })
});