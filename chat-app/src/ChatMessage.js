import React, { Component } from 'react';

export default class ChatMessage extends Component {
    onClick(){
        this.props.onLogin(this.props.user);
    }
    render() {
        return (
            <div>
              <div>{this.props.message.user}</div>
              <div>{this.props.message.text}</div>
            </div>
        );
    }
}
