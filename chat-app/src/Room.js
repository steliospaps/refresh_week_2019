import React, { Component } from 'react';

export default class Room extends Component {
    render() {
        return (
            <button
              disabled={this.props.selected?"disabled":""}
              onClick={()=>{this.props.onEnterRoom(this.props.room);} }
            >{this.props.room.name} ({this.props.room.id}) {this.props.room.relevance && "[relevance:"+this.props.room.relevance+"]"}</button>
        );
    }
}
