import React, { Component } from 'react';

export default class Room extends Component {
    render() {
        return (
              <button>{this.props.room.name} ({this.props.room.id})</button>
        );
    }
}
