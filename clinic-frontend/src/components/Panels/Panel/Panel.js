import React, { Component }  from 'react';
import "./Panel.css";
import Doctor from "./Doctor/Doctor"
import { Navbar, Nav, NavItem, NavbarBrand } from 'reactstrap';
import axios from 'axios';

class Panel extends Component{
    state = {
        posts: []
    }

    componentDidMount () {
        axios.get('https://jsonplaceholder.typicode.com/posts')
            .then(response => {
                this.setState({posts: response.data})
            });
    }

    typeSwitch(){
        switch (this.props.type){
            case "doctor":
                return <Doctor />;
            default:
                return null;
        }
    }

    render () {
        const posts = this.state.posts.map(post => {
            return post.title;
        });
        return (
            <div>
                <header className="App-header">
                    <Navbar color="dark" dark>
                        <Nav className="ml-auto" navbar>
                            <NavItem>
                                <p>{this.props.type}</p>
                            </NavItem>
                        </Nav>
                    </Navbar>
                </header>
                <div>
                    {this.typeSwitch()}
                </div>
            </div>
        );
    }
}

export default Panel;