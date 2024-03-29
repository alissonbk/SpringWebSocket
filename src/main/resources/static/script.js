var stompClient = null;
var notificationCount = 0;
var username = null;

$(document).ready(function() {
    console.log("Pagina inical!");
    connect();

    $("#send").click(function() {
        sendMessage();
    });

    $("#send-private").click(function() {
        sendPrivateMessage();
    });

    $("#notifications").click(() => {
        resetNotificationCount();
    })

    /**
     * Subscribe e unsubscribe nas salas
     * */
    $("#substec").click(() => {
        console.log(username);
        axios.get(`/channels/subscribe/technology/${username.toString()}`).then(
            resp => {
                resp.data === true ? $("#substec").css("background-color", "green") : '';
            },
            err => {
                console.log(err);
            }
        )
    });
    $("#subssports").click(() => {
        axios.get(`/channels/subscribe/sports/${username.toString()}`).then(
            resp => {
                resp.data === true ? $("#subssports").css("background-color", "green") : '';
            },
            err => {
                console.log(err);
            }
        )
    });
    $("#subnews").click(() => {
        axios.get(`/channels/subscribe/news/${username.toString()}`).then(
            resp => {
                resp.data === true ? $("#subnews").css("background-color", "green") : '';
            },
            err => {
                console.log(err);
            }
        )
    });
    $("#unsubtec").click(() => {
        axios.get(`/channels/unsubscribe/technology/${username.toString()}`).then(
            resp => {
                resp.data === true ? $("#substec").css("background-color", "") : '';
            },
            err => {
                console.log(err);
            }
        )
    });
    $("#unsubsports").click(() => {
        axios.get(`/channels/unsubscribe/sports/${username.toString()}`).then(
            resp => {
                resp.data === true ? $("#subssports").css("background-color", "") : '';
            },
            err => {
                console.log(err);
            }
        )
    });
    $("#unsubnews").click(() => {
        axios.get(`/channels/unsubscribe/news/${username.toString()}`).then(
            resp => {
                resp.data === true ? $("#subnews").css("background-color", "") : '';
            },
            err => {
                console.log(err);
            }
        )
    });
});

function connect() {
    /**SockJS cria um socket de conexao websocket com a api*/
    var socket = new SockJS('/websocket-handshake');
    /**
     * Stomp é responsavel por realizar as ações sobre o socket de conexão criado com o SockJS
     * basicamente ele faz o envio das mensagens e também se inscreve no topic para receber as mensagens
     * */
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log('Conectado: ' + frame);
        username = frame.headers['user-name'];
        updateNotificationDisplay();

        stompClient.subscribe('/topic/messages', msg => {
            showMessage(JSON.parse(msg.body).content);
        });
        stompClient.subscribe('/user/topic/private-messages', msg => {
            showMessage(JSON.parse(msg.body).content);
        });

        stompClient.subscribe('/topic/global-notification', function (message) {
            notificationCount++;
            updateNotificationDisplay();
        });
        stompClient.subscribe('/user/topic/private-notification', function (message) {
            notificationCount++;
            updateNotificationDisplay();
        });
    });
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function sendMessage() {
    console.log("Enviando mensagem");
    stompClient.send("/ws/message",
        {},
        JSON.stringify({'messageContent': $("#message").val(), 'delay': parseInt($("#delay").val())}),
    );
}

function sendPrivateMessage() {
    console.log("Enviando mensagem privada");
    stompClient.send("/ws/private-message",
        {},
        JSON.stringify({'messageContent': $("#private-message").val(), 'delay': parseInt($("#delay").val())}));
}

function updateNotificationDisplay(){
    if(notificationCount == 0){
        $('#notifications').hide();
    }else {
        $('#notifications').show();
        $('#notifications').text(notificationCount);
    }

}

function resetNotificationCount(){
    notificationCount = 0;
    updateNotificationDisplay();
}

