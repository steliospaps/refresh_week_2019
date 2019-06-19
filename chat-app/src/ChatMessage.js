import React, { Component } from 'react';
import './ChatMessage.css';

export default class ChatMessage extends Component {
    onClick(){
        this.props.onLogin(this.props.user);
    }
    render() {
        return (
            <div className="message">
              <div className="user">{this.props.message.user}: </div>
              <div className="message">{this.props.message.text}</div>
            </div>
        );
    }
}
