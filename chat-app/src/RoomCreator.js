import React from 'react';
import './RoomCreator.css';
import { Mutation } from 'react-apollo';
import gql from 'graphql-tag';

const CREATE_ROOM=gql`
mutation 
  PostMutation($name:String!){
     createRoom(name:$name){id}
}`;

export default class RoomCreator extends React.Component {
    state = {
        text:''
    };
    render() {
        const {text} = this.state; 
        return(
            <Mutation mutation={CREATE_ROOM}
                      variables = {{name: text}}
            >
              {(postMutation,{loading,error}) =>{
                  return (
                      <div>                      
                        <input
                          value={this.state.text}
                          type="text"
                          disabled={loading?"disabled":""}
                          placeholder="room name"
                          onChange={ e =>{this.setState({text:e.target.value});}} 
                        />
                        <button onClick={
                            () =>{
                                postMutation();
                                this.setState({text:''});
                            }
                        }
                                disabled={loading?"disabled":""}
                        >Create</button>
                        {error && <label>there was an error</label>}
                      </div>
                  );}}
            </Mutation>
        );
    }
}
