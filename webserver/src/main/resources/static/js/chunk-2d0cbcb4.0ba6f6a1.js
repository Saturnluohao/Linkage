(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0cbcb4"],{"4ad5":function(t,e,i){"use strict";i.r(e);var a=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("Button",{staticStyle:{margin:"10px"},attrs:{type:"primary",shape:"circle",icon:"ios-arrow-back",to:{path:"subscription",name:"subscription",params:{currentPage:t.currentPage}}}},[t._v("Back")]),i("h1",{staticStyle:{"margin-top":"10px","text-align":"center"}},[t._v("Subscription Detail")]),i("Divider"),i("div",{staticStyle:{width:"500px",margin:"0 auto"}},[i("Card",{attrs:{title:"Global",icon:"md-people",padding:0,shadow:""}},[i("CellGroup",[i("Cell",[i("div",{staticStyle:{display:"flex","justify-content":"space-around"}},[i("div",{staticClass:"horizontal"},[i("Avatar",{attrs:{shape:"circle",src:t.iconURL,icon:"ios-people",size:"100"}})],1),i("div",{staticClass:"horizontal"},[i("h1",{staticStyle:{"margin-top":"10px"}},[t._v(" "+t._s(t.username)+" ")]),i("ButtonGroup",{staticStyle:{"margin-top":"15px"},attrs:{size:"large",shape:"circle"}},[i("Button",[t._v("Subscription: "+t._s(t.subscription))]),i("Button",[t._v("Follower: "+t._s(t.follower))])],1)],1)])]),i("Cell",{attrs:{title:"Descrption",label:t.description}}),i("Cell",{attrs:{title:"Its Posts",to:{path:"itsPosts",name:"itsPosts",params:{subscriptionName:t.username,currentPage:t.currentPage}}}}),i("Cell",{attrs:{title:"Shielding"}},[i("i-switch",{attrs:{slot:"extra"},slot:"extra",model:{value:t.ShieldingValue,callback:function(e){t.ShieldingValue=e},expression:"ShieldingValue"}})],1)],1)],1)],1)],1)},s=[],r={data:function(){return{username:"",description:"",subscription:0,follower:0,iconURL:"",ShieldingValue:!1,currentPage:0}},mounted:function(){this.username=this.$route.params.subscriptionName,this.currentPage=this.$route.params.currentPage},methods:{}},n=r,l=i("2877"),o=Object(l["a"])(n,a,s,!1,null,null,null);e["default"]=o.exports}}]);
//# sourceMappingURL=chunk-2d0cbcb4.0ba6f6a1.js.map