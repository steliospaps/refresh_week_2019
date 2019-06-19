import React from 'react';
import './UserCreator.css';
import { Mutation } from 'react-apollo';
import gql from 'graphql-tag';

const CREATE_USER=gql`
mutation 
  PostMutation($name:String!){
     createUser(name:$name){id}
}`;

export default class UserCreator extends React.Component {
    state = {
        text:''
    };
    render() {
        const {text} = this.state; 
        return(
            <Mutation mutation={CREATE_USER}
                      variables = {{name: text}}
            >
              {(postMutation,{loading,error}) =>{
                  return (
                      <div>                      
                        <input
                          value={this.state.text}
                          type="text"
                          disabled={loading?"disabled":""}
                          placeholder="user name"
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
