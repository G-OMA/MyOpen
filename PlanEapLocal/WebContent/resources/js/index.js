/**
 * Author: Goma OMA1989@YEAH.NET 
 * DESC: This javascript file is maded for index.jsp
 */
function zTreeOnClick(event, treeId, treeNode) {
	if(treeNode.homeUrl!=""){
		$("#workspace").addTab(treeNode.name,PlanEap.getActionURI(treeNode.homeUrl),true);
	}
}

function filter(treeId, parentNode, childNodes) {
	if (!childNodes) return null;
	var nodes = new Array();
	var pid = $("#navMenu").find(".selected>a").attr("id");
	for (var i=0, l=childNodes.length; i<l; i++) {
		if(childNodes[i].pid == pid)
			nodes.push(childNodes[i]);
	}
	return nodes;
}

$(document).ready(function() {
	/** *****************横向菜单******************* */
	$.fn.navMenu = function() {
		return this.each(function(idx) {
			var $box = $(this);
			if(idx==0){
				var setting = {
						async: {
					        enable: true,
				            url:PlanEap.getActionURI("findUserMenus"),
				            autoParam:["id=mid"],
				            otherParam: ["pmid",$box.find("li>a").first().attr("id")],
						    dataFilter: filter
			            },
						data: { simpleData: { enable: true } },
						callback: { onClick: zTreeOnClick }
					};
				$.fn.zTree.init($("#navTree"), setting);
				$box.find("li").first().addClass("selected");
			}
			$box.find("li>a").click(function() {
				var $a = $(this);
				var setting = {
					async: {
					   enable: true,
					   url:PlanEap.getActionURI("findUserMenus"),
					   autoParam:["id=mid"],
					   otherParam: ["pmid",this.id],
					   dataFilter: filter
					},
					data: { simpleData: { enable: true } },
					callback: { onClick: zTreeOnClick }
				};
				$("#navTree").html('');
				$.fn.zTree.init($("#navTree"), setting);
				$box.find("li").removeClass("selected");
				$a.parent().addClass("selected");
				return false;
			});
		});
	};
	if ($.fn.navMenu)
		$("#navMenu").navMenu();

	/** *****************tabPanel****************** */
	$('#workspace').tabs( {
		border : false,
		fit : true,
		plain : false,
		cache : false
	});
	$.fn.extend( {
		addTab : function(title, url,cache) {
			$aim = $(this);
			if ($aim.tabs('exists', title)) {
				$aim.tabs('select', title);
			} else {
				$aim.tabs('add', {
					title : title,
					href : url,
					fit : true,
					closable : true,
					cache : cache,
					extractor : function(data) {
					   if(typeof(data) == "string")
						   return data;
					}
				});
			}
		},
		addWelcomeTab : function(title, url) {
			$aim = $(this);
			if ($aim.tabs('exists', title)) {
				$aim.tabs('select', title);
			} else {
				$aim.tabs('add', {
					title : title,
					href : url,
					fit : true,
					closable : false,
					cache : false,
					extractor : function(data) {
					   if(typeof(data) == "string")
				          return data;
					}
				});
			}
		}
	});
});