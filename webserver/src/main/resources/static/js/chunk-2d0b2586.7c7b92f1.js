(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0b2586"],{"245c":function(e,t,o){"use strict";o.r(t);var s=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{staticStyle:{margin:"auto"}},[o("br"),o("h1",{staticStyle:{"text-align":"center"}},[e._v("Forget Password")]),o("Divider"),o("Form",{staticStyle:{margin:"auto"},attrs:{model:e.formItem,"label-width":80}},[o("FormItem",{attrs:{label:"Phone"}},[o("div",[o("Input",{attrs:{placeholder:"Enter phone number"},model:{value:e.formItem.phone,callback:function(t){e.$set(e.formItem,"phone",t)},expression:"formItem.phone"}}),o("Input",{attrs:{search:"","enter-button":"Get code",placeholder:"Enter validation code"},on:{"on-search":function(t){return e.getCode()}},model:{value:e.formItem.code,callback:function(t){e.$set(e.formItem,"code",t)},expression:"formItem.code"}})],1)]),o("FormItem",{attrs:{label:"PassWord"}},[o("Input",{attrs:{type:"password",password:"",placeholder:"Enter password"},model:{value:e.formItem.password1,callback:function(t){e.$set(e.formItem,"password1",t)},expression:"formItem.password1"}}),o("Input",{attrs:{type:"password",password:"",placeholder:"Ensure password"},model:{value:e.formItem.password2,callback:function(t){e.$set(e.formItem,"password2",t)},expression:"formItem.password2"}})],1),o("FormItem",[o("Button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("Submit")]),o("Button",{staticStyle:{"margin-left":"8px"},on:{click:function(t){return e.cancel()}}},[e._v("Cancel")])],1)],1)],1)},r=[],a={data:function(){return{formItem:{password1:"",password2:"",phone:"",code:""}}},methods:{getCode:function(){var e=this,t="/forget/code?phone="+this.formItem.phone;this.$axios.get(t).then((function(t){e.$Message.success("The code is sent to your phone!")})).catch((function(t){e.$Message.error("There is some errors.")}))},submitForm:function(){var e=this;if(this.formItem.password1!=this.formItem.password2)this.formItem.password1="",this.formItem.password2="",this.$Message.error("The passwords you entered aren't matched.");else{var t="http://localhost:8080/forget/user";this.$axios.post(t,{phoneNumber:this.formItem.phone,userPassword:this.formItem.password1,verificationCode:this.formItem.code}).then((function(t){e.$Message.success("Successfully change password"),e.$router.push("log")})).catch((function(t){e.$Message.error("Unsuccessfully change password"),console.log(t)}))}},cancel:function(){this.$router.push("log")}}},n=a,c=o("2877"),m=Object(c["a"])(n,s,r,!1,null,null,null);t["default"]=m.exports}}]);
//# sourceMappingURL=chunk-2d0b2586.7c7b92f1.js.map