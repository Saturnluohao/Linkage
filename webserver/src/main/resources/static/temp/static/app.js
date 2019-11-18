var stompClient = null;
function serConnected(connected) {
  $("#connect").prop("disabled",connected);
  $("#disconnect").prop("disabled",!connected);
  if(connected){
    $("#conversation").show();
    $("#chat").show();
  }
  else {
    $("#conversation").hide();
    $("#chat").hide();
  }
  $("#greetings").html("");
}
function connect() {
  if(!$("#name").val()){
    return;
  }
  console.log("i know");
  $.ajax(
    {
      url:'http://localhost:8080/login?name='+ $("#name").val() +'&passwd=123',
      method:'POST',
      success:function () {
        console.log('login success')
      },
      error:function (e) {
        console.log(e)
      }
    }
  );
  var socket = new SockJS('/chat');
  stompClient = Stomp.over(socket);
  stompClient.connect({},function (frame) {
    serConnected(true);
    stompClient.subscribe('/user/queue/chat',function (greeting) {
      showGreeting(JSON.parse(greeting.body))
    })
  })
}
function showGreeting(message) {
  console.log(message);
  $("#greetings").append("<div>"+message.name+":"+message.content+"</div>")
}
function disconnect() {
  if(stompClient!==null){
    stompClient.disconnect();
  }
  serConnected(false);
}
function sendName(){
  console.log("hello");
  stompClient.send("/app/chat",{},JSON.stringify(
    {
      'to':$("#to").val(),
      'content':$("#content").val(),
      'name':$("#name").val()
    }
  ))
}
$(function () {
  console.log("hello!")
  $("#connect").click(function () {
    connect();
  });
  $("#disconnect").click(function () {
    disconnect();
  });
  $("#send").click(function () {
    sendName();
  })
});
