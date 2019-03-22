'use strict';

var usernameFormForPrivateChatting = document.querySelector('#usernameFormForPrivateChatting');
var privateChatMessageForm = document.querySelector('#privateChatMessageForm');
var privateMessageInput = document.querySelector('#privateMessage');
var privateChatMessageArea = document.querySelector('#privateChatMessageArea');

var privateChatStompClient = null;

var username = null;
var privateChatUsername = null;

function initializationBeforePrivateChatting(event){
    username = document.querySelector('#name').value.trim();
    privateChatUsername = document.querySelector('#usernameForPrivateChat').value.trim();
    console.log(privateChatUsername);
    if(username && privateChatUsername){
        var socket = new SockJS('/ws');
        privateChatStompClient = Stomp.over(socket);
        privateChatStompClient.connect({}, onConnectedToPrivateChat, onErrorPrivateChat);
    }
    event.preventDefault();
}


function onConnectedToPrivateChat(){
    privateChatStompClient.subscribe('/user/' + privateChatUsername + '/sendTo', onMessageReceivedForPrivateChat);
    privateChatStompClient.subscribe('/user/' + username + '/receive', onMessageReceivedForPrivateChat);
    privateChatStompClient.send("/app/chat.sendToPrivateChat", {},
        JSON.stringify({
            sender: {
                username: username
            },
            receiver: {
              username: privateChatUsername
            },
            text: username + ' joined to chat!',
            messageType: 'JOIN'
        })
    )
}

function onErrorPrivateChat(error) {
    alert("onErrorPrivateChat");
}

function sendMessageForPrivateChat(event){
    var messageContent = privateMessageInput.value.trim();
    if(messageContent && privateChatStompClient){
        var chatMessage = {
            sender: {
                username: username
            },
            receiver: {
              username: privateChatUsername
            },
            text: privateMessageInput.value,
            messageType: 'CHAT'
        };
        stompClient.send("/app/chat.sendToPrivateChat", {}, JSON.stringify(chatMessage));
        privateMessageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceivedForPrivateChat(payload) {
    var message = JSON.parse(payload.body);
    var messageElement = document.createElement('li');
    if(message.messageType === 'JOIN'){
        messageElement.classList.add('event-message');
    } else if(message.messageType === 'LEAVE'){
        messageElement.classList.add('event-message');
    } else {
        messageElement.classList.add('chat-message');
        message.text = message.text + ' from ' + message.sender.username + ' to ' + message.receiver.username;
    }
    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.text);
    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);
    privateChatMessageArea.appendChild(messageElement);
    privateChatMessageArea.scrollTop = privateChatMessageArea.scrollHeight;
}

usernameFormForPrivateChatting.addEventListener('submit', initializationBeforePrivateChatting, true);
privateChatMessageForm.addEventListener('submit', sendMessageForPrivateChat, true);