
<script src="/js/sockjs-0.3.4.min.js"></script>
<script src="/js/stomp.min.js"></script>
<script type="text/javascript">
    /*<![CDATA[*/
        var stompClient = null;

        function setConnected(connected) {
//            document.getElementById('connect').disabled = connected;
//            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
            document.getElementById('response').innerHTML = '';
        }

        function connect() {
            var socket = new SockJS('/hello');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                setConnected(true);
                stompClient.subscribe('/topic/greetings', function(greeting){
                    showGreeting(JSON.parse(greeting.body));
                });
            });
        }

        function disconnect() {
            stompClient.disconnect();
            setConnected(false);
            console.log("Disconnected");
        }

      

        function showGreeting(message) {
        	var msg = message.name + " say : " + message.content;
            var response = document.getElementById('response');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(msg));
            response.appendChild(p);
        }
        $(document).ready(function(){
        	
        	$(".msg").keypress(function(e) {
        	    if(e.which == 13) {
        	    	  stompClient.send("/app/hello", {}, JSON.stringify({ 'content': $(".msg").val()}));
         	    }
        	});
        	
        	$(".login").click(function(){
        		$.ajax({
						type : "POST",
						url  : "/login",
						data : $("#login-form").serialize(),
						dataType : "json",
						success : function(data) {
							if (data.code == 200){
 								connect(); 
							$("#login-form").empty();
							}else{
								
							}
						}
					}
					);
        	});
        	
        });
    	/*]]>*/
    </script>


<div class="row">

	<div class="col-md-12">
			<form id="login-form">
	            <div class="block">
                    <div class="header">
                        <h2>Login</h2>
                    </div> 
                    <div class="content controls">
                        <div class="form-row">
                            <div class="col-md-3">UserName:</div>
                            <div class="col-md-9"><input type="text"  name="username" class="form-control" value="" autocomplete="off"/></div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-3">Password:</div>
                            <div class="col-md-9"><input type="password"  name="passsword" class="form-control" autocomplete="off" value=""/></div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-3"> </div>
                            <div class="col-md-9"> <button  type="button" class="btn login"  >Connect</button>
					<button  type="button"   class="btn" id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button></div>
                        </div>
                     </div>
                </div> 
                </form>               
		<div>
			<div>

			</div>
			<div id="conversationDiv">
				<label>Enter your message</label><input type="text" class="msg" autocomplete="off" />
				<!-- 
				<button  class="btn" id="sendName" onclick="sendName();">Send</button>
				 -->
				<p id="response"></p>
			</div>
		</div>
	</div>
</div>