import React, { Component } from 'react';
import { Query } from 'react-apollo';
import gql from 'graphql-tag';
import ChatMessage from './ChatMessage';
import './ChatMessages.css';

export default class ChatMessages extends Component {
    render() {
        return (
	    <Query query={gql`query {room:getRoom(id:1){chatMessages{user{name},message,id}}}`} >
	      {({ loading, error, data }) => {
		  if (loading) return <div>Fetching</div>;
		  if (error) return <div>Error</div>;
		  
		  return (
		      <div className="container" >{data.room.chatMessages.map(msg => <ChatMessage key={msg.id} message={{user:msg.user.name,text:msg.message}}/>)}</div>
		  );
	      }}
	    </Query>
        );
  }
}
