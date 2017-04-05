import React, {Component, PropTypes} from 'react';
import next from '../img/next.svg'

const propTypes = {};
const defaultProps = {};
class PostWriter extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div className="Card Post-Writer">
                <div className="Title-Input-Div">
                    <label className="Write-Content-Label"></label>
                    <p>강렬한 제목을 입력하세요.</p>
                </div>
                <input className="Title-Input" type="text" placeholder="제목을 입력하세요."></input>
                <a className="Next-A"><img className="Next" src={next} alt="next"></img></a>
                {/* <textarea className="Write-Content"></textarea> */}
            </div>
        );
    }
}
PostWriter.propTypes = propTypes;
PostWriter.defaultProps = defaultProps;
export default PostWriter;
