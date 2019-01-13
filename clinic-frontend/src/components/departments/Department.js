import React, {Component} from 'react';

class Department extends Component {
    constructor(props){
        super(props)
    }
    
    render(){
        console.log(this.props.data)
        return(
            <div className="department">
                <p className="label">name:</p>
                <p>{this.props.data.name}</p>
                {/* <div className="controls">
                    <button className="controls-btn edit">Edit</button>
                    <button className="controls-btn delete">Delete</button>
                </div> */}
            </div>
        );
    }
}

export default Department;