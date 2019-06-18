import React, { Component } from 'react';

export default class User extends Component {
    onClick(){
        this.props.onLogin(this.props.user);
    }
    render() {
        return (
            <button onClick={this.onClick.bind(this)}>{this.props.user.name} ({this.props.user.id})</button>
        );
    }
}
