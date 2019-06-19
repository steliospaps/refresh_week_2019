import React from 'react';
import './ChatMessageSender.css';
import { Mutation } from 'react-apollo';
import gql from 'graphql-tag';

const CREATE_CHAT_MESSAGE=gql`
mutation 
  PostMutation($message:String!,$userId:ID!,$roomId:ID!){
     createChatMessage(userId:$userId,
                       roomId:$roomId,
                       message:$message){
     id
     message}
}`;

/**
props: user:{name:,id:}
*/
export default class ChatMessageSender extends React.Component {
    state = {
        text:'original text'
    };
    render() {
        const userId=1;
        const roomId=1;
        const {text} = this.state; 
        return(
            <div>
              <input
                value={this.state.text}
                type="text"
                placeholder="chat message"
                onChange={ e =>{this.setState({text:e.target.value});}} 
              />
              <Mutation mutation={CREATE_CHAT_MESSAGE}
                        variables = {{message: text,roomId: roomId, userId:userId }}
              >
                {(postMutation) =>{
                    return (
                        <button onClick={
                            postMutation
                        }>Send</button>
                    );}}
              </Mutation>
            </div>
        );
    }
}
