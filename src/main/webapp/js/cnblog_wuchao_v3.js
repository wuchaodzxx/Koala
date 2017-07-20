
	function prependChild(parent,newChild){
	    if(parent.firstChild){
	        parent.insertBefore(newChild,parent.firstChild);
	    } else {
	        parent.appendChild(newChild);
	    }
	    
	    return parent;
}
		//var home = document.getElementById("home");
		
		//var documents = document.getElementsByTagName("h1");
		var con =document.getElementById("cnblogs_post_body");
		var headTags = con.getElementsByTagName("h1");
		var L = headTags.length;
		var titleContent = document.createElement("div");//标题存放区
		
		titleContent.setAttribute("style","border:1px dashed  #ffffff;");
		titleContent.setAttribute("id","titleContent");
		var titleTag = document.createElement("div");//显示目录字样
		titleTag.innerHTML="<div style='font-size:20px;color:#ffffff'><strong>目录</strong></div>"
		
		titleContent.appendChild(titleTag);
		
		var titleOl = document.createElement("ol");//标题存放区
		titleOl.setAttribute("style","margin-left:20px;");
		var tagName = "H1_";
		for(var i=0;i<L;i++){
			//tagName=tagName+i;
			////////////////////////////这里写主要代码
			headTags[i].setAttribute("id",tagName+i);
			
			var headNode = document.createElement("li");//当前标题节点
			var hrefNode =  document.createElement("a");
			hrefNode.setAttribute("href","#"+tagName+i);
			//hrefNode.setAttribute("style","white-space:normal");
			hrefNode.innerHTML = headTags[i].innerHTML;
			headNode.appendChild(hrefNode);
			/////查找二级标题
				var titleOl_2 = document.createElement("ol");//二级标题存放区
				titleOl_2.setAttribute("style","margin-left:30px;");
				var flag=0;
				var nextNode = headTags[i].nextSibling;
				while(true){
					
					if(nextNode==null||nextNode==undefined){
						
						break;
					}
					if(nextNode.nodeName=="H1"||nextNode.nodeName=="h1"||nextNode.nodeName== undefined ||nextNode.nodeName== null){
						
						break;
					}
					if(nextNode.nodeName=="H2"||nextNode.nodeName=="h2"){
						flag=flag+1;
						////////////////插入二级标题
						nextNode.setAttribute("id",tagName+i+"_"+flag);
						var headNode2 = document.createElement("li");//当前标题节点
						var hrefNode =  document.createElement("a");
						hrefNode.setAttribute("href","#"+tagName+i+"_"+flag);
						//hrefNode.setAttribute("style","white-space:normal");
						hrefNode.innerHTML = nextNode.innerHTML;
						headNode2.appendChild(hrefNode);
						titleOl_2.appendChild(headNode2);
						////////////////
					}
					nextNode = nextNode.nextSibling;
					
				}
				if(flag>0){
					headNode.appendChild(titleOl_2);
				}
			/////
			
			titleOl.appendChild(headNode);
			
	
			
		}
		if(L<=0){
			titleContent.setAttribute("style","display:none;");
		}
		titleContent.appendChild(titleOl);
		prependChild(con,titleContent);
		
		
		
	
