'use strict';

var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');

var stompClient = null;
var username = null;

function connect(event) {
    username = document.querySelector('#name').value.trim();
    if(username){
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}

function onConnected(){
    stompClient.subscribe('/topic/public', onMessageReceived);
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({
            sender: {
                username: username
            },
            messageType: 'JOIN',
            text: username + ' joined to chat!',
            timestamp: new Date()
        })
    )
}

function onError(error) {
    alert("onError");
}

function sendMessage(event){
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient){
        var chatMessage = {
            sender: {
                username: username
            },
            text: messageInput.value,
            messageType: 'CHAT',
            timestamp: new Date()
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    console.log(message);
    var messageElement = document.createElement('li');
    if(message.messageType === 'JOIN'){
        messageElement.classList.add('event-message');
    } else if(message.messageType === 'LEAVE'){
        messageElement.classList.add('event-message');
    } else {
        messageElement.classList.add('chat-message');
        message.text = message.text + ' from ' + message.sender.username;
    }
    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.text);
    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

usernameForm.addEventListener('submit', connect, true);
messageForm.addEventListener('submit', sendMessage, true);