import React, {Component} from 'react';

class PanelBtn extends Component{
    render(){
        return(
            <div>
                <p onClick={this.props.click}>{this.props.title}</p>
            </div>
        );
    }
}

export default PanelBtn;