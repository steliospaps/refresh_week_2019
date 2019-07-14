import React, { Component } from 'react';
import { Query } from 'react-apollo';
import gql from 'graphql-tag';
import User from './User';
import UserCreator from './UserCreator.js';

const QUERY_USERS=gql`query {users:getAllUsers{id,name}}`;
const SUBSCRIBE_USERS=gql`subscription {user:newUsers{id,name}}`;

class StreamingUsers extends Component {
    componentDidMount(){
        this.props.subscribe();
    }
    render(){
        return (
	    <div>{this.props.data.users.map(user => <User key={user.id} user={user} onLogin={this.props.onLogin} />)}</div>
        );
    }

}

export default class Users extends Component {
    render() {
	if(this.props.user == null) {
            return (
                <div>
		<Query query={QUERY_USERS} >
		  {({subscribeToMore, loading, error, data }) => {
		      if (loading) return <div>Fetching</div>;
		      if (error) return <div>Error</div>;
		      
		      return (
                          <StreamingUsers
                            data={data}
                            onLogin={this.props.onLogin}
                            subscribe={()=>subscribeToMore({
                                document: SUBSCRIBE_USERS,
                                updateQuery: (prev,{subscriptionData}) => {
                                    if(!subscriptionData.data) return prev;
                                    const user = subscriptionData.data.user;
                                    const res = Object.assign({},
                                                              prev,
                                                              {
                                                                  users: [...prev.users,user]
                                                            });
                                  return res;

                                }
                            })}
                          />
		      );
		  }}
		</Query>
                <UserCreator/>
                </div>
            );
	} else {
            return (
                <div>
                  <label>{this.props.user.name}</label> <button onClick={()=>this.props.onLogin(null)}>Logout</button>
                </div>);
        }
  }
}
