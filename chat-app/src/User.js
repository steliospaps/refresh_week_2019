import React, { Component } from 'react';

export default class User extends Component {
    render() {
        return (
              <button>{this.props.user.name} ({this.props.user.id})</button>
        );
    }
}
