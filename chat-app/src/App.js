import React from 'react';
import logo from './logo.svg';
import './App.css';

// see https://www.apollographql.com/
import { ApolloProvider } from 'react-apollo';
import { ApolloClient } from 'apollo-client';
import { createHttpLink } from 'apollo-link-http';
import { InMemoryCache } from 'apollo-cache-inmemory';
import { WebSocketLink } from 'apollo-link-ws';
import { split } from 'apollo-link';
import { getMainDefinition } from 'apollo-utilities';

import Users from './Users';
import Rooms from './Rooms';
import ChatMessages from './ChatMessages';
import ChatMessageSender from './ChatMessageSender.js';
import DummySubscription from './DummySubscription.jsx';

const httpLink = createHttpLink({
  uri: 'http://localhost:8080/graphql'
});

const wsLink = new WebSocketLink({
  uri: `ws://localhost:8080/subscriptions`,
  options: {
    reconnect: true
  }
});

// see https://www.apollographql.com/docs/react/advanced/subscriptions/
// using the ability to split links, you can send data to each link
// depending on what kind of operation is being sent
const link = split(
    // split based on operation type
    ({ query }) => {
        const definition = getMainDefinition(query);
        return (
            definition.kind === 'OperationDefinition' &&
                definition.operation === 'subscription'
        );
    },
    wsLink,
    httpLink,
);

const client = new ApolloClient({
  link: link,
  cache: new InMemoryCache()
});


export default class App extends React.Component {
    constructor(props){
        super(props);
        this.state={};
    }
    onLogin(user){
        this.setState({user:user});
    }
    render() {
        return (
            <div className="App">
	      <ApolloProvider client={client}>
                <DummySubscription/>
                <Users user={this.state.user} onLogin={this.onLogin.bind(this)}/>
                <Rooms/>
                <ChatMessageSender user={this.state.user} />
                <ChatMessages/>
              </ApolloProvider>,
            </div>
        );
    }
}

